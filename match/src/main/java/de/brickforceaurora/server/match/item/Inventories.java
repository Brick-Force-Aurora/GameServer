package de.brickforceaurora.server.match.item;

import java.util.ArrayList;
import java.util.List;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.InventoryItem;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundShooterToolListPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundWeaponSlotListPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.ItemUsage;
import de.brickforceaurora.server.net.tcp.protocol.data.ToolSlot;

//TODO Inventory wie bei Minecraft
public final class Inventories {

    public static final String ATTR_INVENTORY = "Inventory";

    private Inventories() {
        throw new UnsupportedOperationException();
    }

    public static IClientboundPacket[] slotPackets(final List<InventoryItem> items) {
        final List<ToolSlot> weapons = new ArrayList<>();
        final List<ToolSlot> tools = new ArrayList<>();
        for (final InventoryItem item : items) {
            if (item.toolSlot() < 0) {
                continue;
            }
            if (isWeapon(item)) {
                weapons.add(new ToolSlot(item.toolSlot(), item.id()));
            } else if (isTool(item)) {
                tools.add(new ToolSlot(item.toolSlot(), item.id()));
            }
        }
        return new IClientboundPacket[] {
            new ClientboundWeaponSlotListPacket().slots(weapons.toArray(ToolSlot[]::new)),
            new ClientboundShooterToolListPacket().slots(tools.toArray(ToolSlot[]::new))
        };
    }

    // The inventory is kept at the client, reduces db calls but manually updating the inventory after each change is necessary
    public static List<InventoryItem> inventory(final UserRepository repository, final BFClient client) {
        final List<InventoryItem> cached = client.attr(ATTR_INVENTORY, List.class); // TODO: WTF?!??!?!
        if (cached != null) {
            return cached;
        }
        final Integer accountId = client.attr("AccountId", Integer.class);
        if (accountId == null) {
            return List.of();
        }
        final List<InventoryItem> items = List.copyOf(repository.loadInventory(accountId));
        client.attrSet(ATTR_INVENTORY, items);
        return items;
    }

    public static void changed(final BFClient client) {
        client.attrUnset(ATTR_INVENTORY);
    }

    public static List<String> equipment(final UserRepository repository, final BFClient client) throws DatabaseException {
        final List<String> codes = new ArrayList<>();
        for (final InventoryItem item : inventory(repository, client)) {
            if (item.usage() == ItemUsage.EQUIP.id()) {
                codes.add(item.code());
            }
        }
        return codes;
    }

    public static boolean hasPremium(final List<InventoryItem> items) {
        for (final InventoryItem item : items) {
            if (ItemTable.grantsPremium(item.code()) && item.remain() != 0 && item.usage() >= ItemUsage.UNEQUIP.id()
                && item.usage() <= ItemUsage.EQUIP.id()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWeapon(final InventoryItem item) {
        final ItemEntry entry = ItemTable.of(item.code());
        return entry != null && entry.weapon();
    }

    public static boolean isTool(final InventoryItem item) {
        final ItemEntry entry = ItemTable.of(item.code());
        return entry != null && entry.tool();
    }

}
