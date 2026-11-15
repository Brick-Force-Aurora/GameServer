package de.brickforceaurora.server.match.game.base;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.InventoryItem;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.match.game.GameTarget;
import de.brickforceaurora.server.match.game.IGameNetListener;
import de.brickforceaurora.server.match.game.MatchGameData;
import de.brickforceaurora.server.net.protocol.data.DroppedItem;
import de.brickforceaurora.server.match.item.Inventories;
import de.brickforceaurora.server.match.item.ItemEntry;
import de.brickforceaurora.server.match.item.ItemTable;
import de.brickforceaurora.server.match.listener.ClientListener;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDelDropItemPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDropItemPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPickupDroppedItemFailPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPickupDroppedItemPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPlayerWeaponChangePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundWeaponChangePacket;
import de.brickforceaurora.server.net.protocol.data.ItemUsage;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundDropItemPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundPickupDroppedItemPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundUseShooterConsumablePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundWeaponChangePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundWeaponHeldRatioPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(value = GameMode.class, data = MatchGameData.class)
public class ItemListener_ implements IGameNetListener<MatchGameData> {

    @PacketHandler
    public void onDropItem(Room room, MatchGameData data, NetContext<ServerboundDropItemPacket> context) {
        ServerboundDropItemPacket packet = context.packet();
        String code = packet.itemCode();
        if (room.status() != RoomStatus.PLAYING || !data.drop() || data.stats(context.client().id()) == null || code == null
            || code.isEmpty() || code.length() > 16 || !Float.isFinite(packet.x()) || !Float.isFinite(packet.y())
            || !Float.isFinite(packet.z())) {
            return;
        }
        DroppedItem item = data.addDrop(code, packet.count(), packet.subCount(), packet.x(), packet.y(), packet.z());
        if (item == null) {
            return;
        }
        room.broadcast(new ClientboundDropItemPacket().item(item));
    }

    @PacketHandler
    public void onPickupDroppedItem(Room room, MatchGameData data, NetContext<ServerboundPickupDroppedItemPacket> context) {
        BFClient client = context.client();
        int itemSeq = context.packet().itemSeq();
        if (room.status() != RoomStatus.PLAYING || !data.drop() || data.stats(client.id()) == null) {
            return;
        }
        if (data.takeDrop(itemSeq) == null) {
            client.send(new ClientboundPickupDroppedItemFailPacket());
            return;
        }
        room.broadcast(new ClientboundPickupDroppedItemPacket().clientId(client.id()).itemSeq(itemSeq),
            new ClientboundDelDropItemPacket().itemSeq(itemSeq));
    }

    @PacketHandler
    public void onWeaponHeldRatio(Room room, MatchGameData data, NetContext<ServerboundWeaponHeldRatioPacket> context) {
        context.manager().logger().debug("{0} held its weapons: {1}", context.client(), Arrays.toString(context.packet().heldRatio()));
    }

    @PacketHandler
    public void onUseShooterConsumable(Room room, MatchGameData data, NetContext<ServerboundUseShooterConsumablePacket> context) {
        BFClient client = context.client();
        Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        if (room.status() != RoomStatus.PLAYING || accountId == null) {
            return;
        }
        ServerboundUseShooterConsumablePacket packet = context.packet();
        try {
            UserRepository users = new UserRepository(MatchServerApp.database());
            Optional<InventoryItem> found = users.loadInventory(accountId).stream()
                .filter(item -> item.id() == packet.item() && item.code().equals(packet.code())).findFirst();
            if (found.isEmpty()) {
                return;
            }
            InventoryItem item = found.get();
            if (item.amount() > 1) {
                users.updateItem(new InventoryItem(item.id(), item.accountId(), item.code(), item.usage(), item.remain(),
                    item.amount() - 1, item.premium(), item.durability(), item.toolSlot()));
            } else {
                users.removeItem(accountId, item.id());
            }
        } catch (DatabaseException exception) {
            context.manager().logger().error("Couldn't use the consumable of {0}", exception, client);
        }
    }


    @PacketHandler
    public void onWeaponChange(Room room, MatchGameData data, NetContext<ServerboundWeaponChangePacket> context) {
        BFClient client = context.client();
        ServerboundWeaponChangePacket packet = context.packet();
        Integer accountId = client.attr(ClientListener.ATTR_ACCOUNT_ID, Integer.class);
        // The client waits for the answer, so there always is one. The result is what the client shows: 0 is fine, -2 is the same weapon
        int result = -1;
        ItemEntry previous = ItemTable.of(packet.prev());
        if (room.status() == RoomStatus.PLAYING && accountId != null && data.stats(client.id()) != null && previous != null
            && previous.weapon()) {
            try {
                List<InventoryItem> items = new UserRepository(MatchServerApp.database()).loadInventory(accountId);
                InventoryItem item = null;
                for (InventoryItem entry : items) {
                    if (entry.id() == packet.seq()) {
                        item = entry;
                        break;
                    }
                }
                ItemEntry next = item == null ? null : ItemTable.of(item.code());
                // The slots from 5 on are only for premium accounts
                if (packet.slot() >= 5 && !Inventories.hasPremium(items)) {
                    result = -4;
                }
                // The weapon has to be the one in the slot the player asked for and the weapon option of the room has to allow it
                else if (next != null && next.weapon() && item.toolSlot() == packet.slot() && item.usage() >= ItemUsage.UNEQUIP.id()
                    && item.usage() <= ItemUsage.EQUIP.id() && changeAllowed(data.weaponOption(), next.category())) {
                    if (item.code().equals(previous.code())) {
                        result = -2;
                    } else {
                        result = 0;
                        room.broadcast(new ClientboundPlayerWeaponChangePacket().clientId(client.id()).prev(previous.code())
                            .next(item.code()));
                    }
                }
            } catch (DatabaseException exception) {
                context.manager().logger().error("Couldn't change the weapon of {0}", exception, client);
            }
        }
        client.send(new ClientboundWeaponChangePacket().result(result).cost(0).item(packet.seq()));
    }
    
    private static boolean changeAllowed(int weaponOption, int category) {
        return switch (weaponOption) {
        case 0 -> true;
        case 1 -> category == 4 || category == 5;
        case 2 -> category == 5;
        default -> false;
        };
    }

}
