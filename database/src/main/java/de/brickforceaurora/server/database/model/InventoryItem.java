package de.brickforceaurora.server.database.model;

import java.util.ArrayList;
import java.util.List;

public record InventoryItem(long id, int accountId, String code, int usage, int remain, int amount, int premium, int durability,
                            int toolSlot) {

    public static final long NEW = 0L;

    private static final String[][] STARTER_ITEMS = {
            {"wau", "1"}, {"wax", "1"}, {"wba", "1"}, {"wap", "1"}, {"aac", "1"}, {"aad", "1"}, {"s07", "1"},
            {"sb7", "0"}, {"sb8", "0"}, {"sb9", "0"}, {"sc0", "0"}, {"sc1", "0"}, {"sc3", "0"}, {"sc4", "0"}, {"s34", "0"}, {"s43", "0"},
            {"s78", "0"}, {"s79", "0"}, {"s22", "0"}, {"s06", "0"}, {"s05", "0"}, {"s27", "0"}
    };

    /**
     * The items every new account starts with
     */
    // TODO: SHOULD BE GIVEN TO THE PLAYER AFTER THE TUTORIAL
    public static List<InventoryItem> starterItems(int accountId) {
        List<InventoryItem> items = new ArrayList<>(STARTER_ITEMS.length);
        for (String[] starter : STARTER_ITEMS) {
            items.add(new InventoryItem(NEW, accountId, starter[0], Integer.parseInt(starter[1]), -1, 1, 0, 1000, -1));
        }
        return items;
    }

    public static InventoryItem create(int accountId, String code, int usage, int remain, int amount) {
        return new InventoryItem(NEW, accountId, code, usage, remain, amount, 0, 0, -1);
    }

}
