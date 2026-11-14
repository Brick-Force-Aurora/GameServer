package de.brickforceaurora.server.match.item;

import java.util.ArrayList;
import java.util.List;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.InventoryItem;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundShooterToolListPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundWeaponSlotListPacket;
import de.brickforceaurora.server.net.protocol.data.ItemUsage;
import de.brickforceaurora.server.net.protocol.data.ToolSlot;

public final class Inventories {

    private Inventories() {
        throw new UnsupportedOperationException();
    }

    public static IClientboundPacket[] slotPackets(final List<InventoryItem> items) {
        List<ToolSlot> weapons = new ArrayList<>();
        List<ToolSlot> tools = new ArrayList<>();
        for (InventoryItem item : items) {
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

    public static List<String> equipment(final BFClient client) {
        Integer accountId = client.attr("AccountId", Integer.class);
        List<String> codes = new ArrayList<>();
        if (accountId == null) {
            return codes;
        }
        try {
            for (InventoryItem item : new UserRepository(MatchServerApp.database()).loadInventory(accountId)) {
                if (item.usage() == ItemUsage.EQUIP.id()) {
                    codes.add(item.code());
                }
            }
        } catch (DatabaseException exception) {
            MatchServerApp.logger().error("Couldn't load the equipment of {0}", exception, client);
        }
        return codes;
    }

    public static boolean hasPremium(final List<InventoryItem> items) {
        for (InventoryItem item : items) {
            if (ItemTable.grantsPremium(item.code()) && item.remain() != 0 && item.usage() >= ItemUsage.UNEQUIP.id()
                && item.usage() <= ItemUsage.EQUIP.id()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWeapon(final InventoryItem item) {
        ItemEntry entry = ItemTable.of(item.code());
        return entry != null && entry.weapon();
    }

    public static boolean isTool(final InventoryItem item) {
        ItemEntry entry = ItemTable.of(item.code());
        return entry != null && entry.tool();
    }

}
