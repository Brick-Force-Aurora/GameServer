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

    private static final String[] ACTION_ITEMS = {
            "s10", "s12", "s13", "s14", "s15", "s16", "s17", "s18", "s19", "s23", "s24"
    };

    private static final String[] WEAPONS = {
            "waa", "waq", "wab", "wah", "wad", "waj", "wae", "wda", "waf", "wbd", "wag", "wak", "wal", "was", "wat", "wbo",
            "wdd", "weg", "wct", "wcu", "wdc", "wcv", "wbt", "wbs", "wbx", "wcz", "wcy", "wfo", "wdp", "wao"
    };

    // TODO: SHOULD BE GIVEN TO THE PLAYER AFTER THE TUTORIAL
    public static List<InventoryItem> starterItems(int accountId) {
        List<InventoryItem> items = new ArrayList<>(STARTER_ITEMS.length + ACTION_ITEMS.length + WEAPONS.length);
        for (String[] starter : STARTER_ITEMS) {
            items.add(new InventoryItem(NEW, accountId, starter[0], Integer.parseInt(starter[1]), -1, 1, 0, 1000, -1));
        }
        for (String code : ACTION_ITEMS) {
            items.add(new InventoryItem(NEW, accountId, code, 0, -1, 1, 0, 1000, -1));
        }
        for (String code : WEAPONS) {
            items.add(new InventoryItem(NEW, accountId, code, 0, -1, 1, 0, 1000, -1));
        }
        return items;
    }

    public static InventoryItem create(int accountId, String code, int usage, int remain, int amount) {
        return new InventoryItem(NEW, accountId, code, usage, remain, amount, 0, 0, -1);
    }

    public InventoryItem withUsage(int usage) {
        return new InventoryItem(id, accountId, code, usage, remain, amount, premium, durability, toolSlot);
    }

    public InventoryItem withToolSlot(int toolSlot) {
        return new InventoryItem(id, accountId, code, usage, remain, amount, premium, durability, toolSlot);
    }

}
