package de.brickforceaurora.server.match.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.InventoryItem;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.item.Inventories;
import de.brickforceaurora.server.match.item.ItemEntry;
import de.brickforceaurora.server.match.item.ItemTable;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundEquipPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundShooterToolListPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundShooterToolPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundUnequipPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundWeaponSlotListPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundWeaponSlotPacket;
import de.brickforceaurora.server.net.protocol.data.ItemUsage;
import de.brickforceaurora.server.net.protocol.data.ToolSlot;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundClearShooterToolsPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundClearWeaponSlotPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundEquipPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundSetShooterToolPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundSetWeaponSlotPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundUnequipPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class InventoryListener_ implements INetListener {

    @PacketHandler
    public void onEquip(final NetContext<ServerboundEquipPacket> context) {
        BFClient client = context.client();
        Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        if (accountId == null) {
            return;
        }
        List<IClientboundPacket> packets = new ArrayList<>();
        try {
            synchronized (client) {
                UserRepository users = new UserRepository(MatchServerApp.database());
                List<InventoryItem> items = users.loadInventory(accountId);
                InventoryItem item = find(items, context.packet().seq());
                ItemEntry entry = item == null ? null : ItemTable.of(item.code());
                // Premium items can only be worn with a premium account
                if (entry == null || !entry.wearable() || item.usage() != ItemUsage.UNEQUIP.id()
                    || (item.premium() == 1 && !Inventories.hasPremium(items))) {
                    return;
                }
                for (InventoryItem other : items) {
                    ItemEntry otherEntry = ItemTable.of(other.code());
                    if (other.id() == item.id() || other.usage() != ItemUsage.EQUIP.id() || otherEntry == null
                        || !otherEntry.wearable() || !otherEntry.slot().equals(entry.slot())) {
                        continue;
                    }
                    users.updateItem(other.withUsage(ItemUsage.UNEQUIP.id()));
                    packets.add(new ClientboundUnequipPacket().clientId(client.id()).item(other.id()).code(other.code()));
                }
                users.updateItem(item.withUsage(ItemUsage.EQUIP.id()));
                packets.add(new ClientboundEquipPacket().clientId(client.id()).item(item.id()).code(item.code()));
            }
        } catch (DatabaseException exception) {
            context.manager().logger().error("Couldn't equip the item of {0}", exception, client);
            return;
        }
        sendToRoom(client, packets);
    }

    @PacketHandler
    public void onUnequip(final NetContext<ServerboundUnequipPacket> context) {
        BFClient client = context.client();
        Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        if (accountId == null) {
            return;
        }
        try {
            synchronized (client) {
                UserRepository users = new UserRepository(MatchServerApp.database());
                InventoryItem item = find(users.loadInventory(accountId), context.packet().seq());
                ItemEntry entry = item == null ? null : ItemTable.of(item.code());
                if (entry == null || !entry.wearable() || !entry.takeoffable() || item.usage() != ItemUsage.EQUIP.id()) {
                    return;
                }
                users.updateItem(item.withUsage(ItemUsage.UNEQUIP.id()));
                sendToRoom(client, List.of(new ClientboundUnequipPacket().clientId(client.id()).item(item.id()).code(item.code())));
            }
        } catch (DatabaseException exception) {
            context.manager().logger().error("Couldn't take off the item of {0}", exception, client);
        }
    }

    @PacketHandler
    public void onSetWeaponSlot(final NetContext<ServerboundSetWeaponSlotPacket> context) {
        BFClient client = context.client();
        Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        int slot = context.packet().slot();
        long weapon = context.packet().weapon();
        if (accountId == null || slot < 0 || slot >= 10) {
            return;
        }
        List<IClientboundPacket> packets = new ArrayList<>();
        try {
            synchronized (client) {
                UserRepository users = new UserRepository(MatchServerApp.database());
                List<InventoryItem> items = users.loadInventory(accountId);
                InventoryItem item = weapon < 0 ? null : find(items, weapon);
                // The slots from 5 on are only for premium accounts
                if (weapon >= 0 && slot >= 5 && !Inventories.hasPremium(items)) {
                    return;
                }
                if (weapon >= 0 && (item == null || !Inventories.isWeapon(item) || item.usage() < ItemUsage.UNEQUIP.id()
                    || item.usage() > ItemUsage.EQUIP.id())) {
                    return;
                }
                for (InventoryItem other : items) {
                    if (other.toolSlot() == slot && Inventories.isWeapon(other) && (item == null || other.id() != item.id())) {
                        users.updateItem(other.withToolSlot(-1));
                    }
                }
                if (item == null) {
                    packets.add(new ClientboundWeaponSlotPacket().slot(slot).item(-1L));
                } else {
                    if (item.toolSlot() >= 0 && item.toolSlot() != slot) {
                        packets.add(new ClientboundWeaponSlotPacket().slot(item.toolSlot()).item(-1L));
                    }
                    users.updateItem(item.withToolSlot(slot));
                    packets.add(new ClientboundWeaponSlotPacket().slot(slot).item(item.id()));
                }
            }
        } catch (DatabaseException exception) {
            context.manager().logger().error("Couldn't set the weapon slot of {0}", exception, client);
            return;
        }
        client.send(packets.toArray(IClientboundPacket[]::new));
    }

    @PacketHandler
    public void onClearWeaponSlots(final NetContext<ServerboundClearWeaponSlotPacket> context) {
        BFClient client = context.client();
        Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        if (accountId == null) {
            return;
        }
        try {
            synchronized (client) {
                UserRepository users = new UserRepository(MatchServerApp.database());
                for (InventoryItem item : users.loadInventory(accountId)) {
                    if (item.toolSlot() >= 0 && Inventories.isWeapon(item)) {
                        users.updateItem(item.withToolSlot(-1));
                    }
                }
            }
        } catch (DatabaseException exception) {
            context.manager().logger().error("Couldn't clear the weapon slots of {0}", exception, client);
            return;
        }
        client.send(new ClientboundWeaponSlotListPacket().slots(IntStream.range(0, 10).mapToObj(slot -> new ToolSlot(slot, -1L)).toArray(ToolSlot[]::new)));
    }

    @PacketHandler
    public void onSetShooterTool(final NetContext<ServerboundSetShooterToolPacket> context) {
        BFClient client = context.client();
        Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        int slot = context.packet().slot();
        long tool = context.packet().tool();
        if (accountId == null || slot < 0 || slot >= 5) {
            return;
        }
        List<IClientboundPacket> packets = new ArrayList<>();
        try {
            synchronized (client) {
                UserRepository users = new UserRepository(MatchServerApp.database());
                List<InventoryItem> items = users.loadInventory(accountId);
                InventoryItem item = tool < 0 ? null : find(items, tool);
                if (tool >= 0 && (item == null || !Inventories.isTool(item) || item.usage() == ItemUsage.DELETED.id())) {
                    return;
                }
                for (InventoryItem other : items) {
                    if (other.toolSlot() == slot && Inventories.isTool(other) && (item == null || other.id() != item.id())) {
                        users.updateItem(other.withToolSlot(-1));
                    }
                }
                if (item == null) {
                    packets.add(new ClientboundShooterToolPacket().slot((byte) slot).item(-1L));
                } else {
                    if (item.toolSlot() >= 0 && item.toolSlot() != slot) {
                        packets.add(new ClientboundShooterToolPacket().slot((byte) item.toolSlot()).item(-1L));
                    }
                    users.updateItem(item.withToolSlot(slot));
                    packets.add(new ClientboundShooterToolPacket().slot((byte) slot).item(item.id()));
                }
            }
        } catch (DatabaseException exception) {
            context.manager().logger().error("Couldn't set the action bar of {0}", exception, client);
            return;
        }
        client.send(packets.toArray(IClientboundPacket[]::new));
    }

    @PacketHandler
    public void onClearShooterTools(final NetContext<ServerboundClearShooterToolsPacket> context) {
        BFClient client = context.client();
        Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        if (accountId == null) {
            return;
        }
        try {
            synchronized (client) {
                UserRepository users = new UserRepository(MatchServerApp.database());
                for (InventoryItem item : users.loadInventory(accountId)) {
                    if (item.toolSlot() >= 0 && Inventories.isTool(item)) {
                        users.updateItem(item.withToolSlot(-1));
                    }
                }
            }
        } catch (DatabaseException exception) {
            context.manager().logger().error("Couldn't clear the action bar of {0}", exception, client);
            return;
        }
        client.send(new ClientboundShooterToolListPacket().slots(IntStream.range(0, 5).mapToObj(slot -> new ToolSlot(slot, -1L)).toArray(ToolSlot[]::new)));
    }

    private static InventoryItem find(final List<InventoryItem> items, final long id) {
        for (InventoryItem item : items) {
            if (item.id() == id) {
                return item;
            }
        }
        return null;
    }

    private static void sendToRoom(final BFClient client, final List<IClientboundPacket> packets) {
        IClientboundPacket[] array = packets.toArray(IClientboundPacket[]::new);
        Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room == null) {
            client.send(array);
            return;
        }
        room.broadcast(array);
    }

}
