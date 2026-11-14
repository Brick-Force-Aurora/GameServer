package de.brickforceaurora.server.match.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.brickforceaurora.server.template.Templates;

/**
 * What the server needs to know about the items of the game: which slot an item is worn in and which items can be put in the action bar.
 * The data comes from the item tables of the game.
 */
public final class ItemTable {

    private static final Map<String, ItemEntry> ENTRIES = load();

    private ItemTable() {
        throw new UnsupportedOperationException();
    }

    /**
     * @return the entry of the item or {@code null} if the item is neither worn nor usable in the action bar
     */
    public static ItemEntry of(final String code) {
        return code == null ? null : ENTRIES.get(code.trim().toLowerCase());
    }

    private static Map<String, ItemEntry> load() {
        Map<String, ItemEntry> entries = new HashMap<>();
        // The columns are the ones the client reads in TItemManager
        add(entries, "weapon", 6, 8);
        add(entries, "costume", 5, 6);
        add(entries, "accessory", 5, 6);
        for (String[] row : Templates.load("character")) {
            entries.put(code(row), new ItemEntry(code(row), false, "character", isTrue(row[4])));
        }
        for (String[] row : Templates.load("special")) {
            // The items of the action bar, the bonus items (xp, fp) can't be put in there
            if (row[3].trim().equalsIgnoreCase("action") && !row[5].trim().toLowerCase().startsWith("consumable_")) {
                entries.put(code(row), new ItemEntry(code(row), true, "", true));
            }
        }
        return Map.copyOf(entries);
    }

    private static void add(final Map<String, ItemEntry> entries, final String table, final int slotColumn, final int takeoffColumn) {
        List<String[]> rows = Templates.load(table);
        for (String[] row : rows) {
            entries.put(code(row), new ItemEntry(code(row), false, row[slotColumn].trim().toLowerCase(), isTrue(row[takeoffColumn])));
        }
    }

    private static String code(final String[] row) {
        return row[0].trim().toLowerCase();
    }

    private static boolean isTrue(final String value) {
        return value.trim().equalsIgnoreCase("true");
    }

}
