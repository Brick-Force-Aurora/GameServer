package de.brickforceaurora.server.match.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.brickforceaurora.server.template.Templates;

/**
 * What the server needs to know about the items of the game: which slot an item is worn in and which items can be put in the action bar.
 * The data comes from the item tables of the game.
 */
public final class ItemTable {

    private static final List<String> WEAPON_CATEGORIES = List.of("heavy", "assault", "sniper", "submachine", "handgun", "melee", "special");

    private static final Map<String, ItemEntry> ENTRIES = load();

    private static final Set<String> PREMIUM_ITEMS = loadPremiumItems();

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
        add(entries, "weapon", 6, 8, 7);
        add(entries, "costume", 5, 6, -1);
        add(entries, "accessory", 5, 6, -1);
        for (String[] row : Templates.load("character")) {
            entries.put(code(row), new ItemEntry(code(row), false, "character", isTrue(row[4]), -1));
        }
        for (String[] row : Templates.load("special")) {
            // The items of the action bar, the bonus items (xp, fp) can't be put in there
            if (row[3].trim().equalsIgnoreCase("action") && !row[5].trim().toLowerCase().startsWith("consumable_")) {
                entries.put(code(row), new ItemEntry(code(row), true, "", true, -1));
            }
        }
        return Map.copyOf(entries);
    }

    /**
     * @return {@code true} if the item is one which makes the account a premium account
     */
    public static boolean grantsPremium(final String code) {
        return code != null && PREMIUM_ITEMS.contains(code.trim().toLowerCase());
    }

    private static Set<String> loadPremiumItems() {
        Set<String> codes = new HashSet<>();
        for (String[] row : Templates.load("special")) {
            if (row[5].trim().equalsIgnoreCase("premium_account")) {
                codes.add(code(row));
            }
        }
        return Set.copyOf(codes);
    }

    private static void add(final Map<String, ItemEntry> entries, final String table, final int slotColumn, final int takeoffColumn,
        final int categoryColumn) {
        List<String[]> rows = Templates.load(table);
        for (String[] row : rows) {
            entries.put(code(row), new ItemEntry(code(row), false, row[slotColumn].trim().toLowerCase(), isTrue(row[takeoffColumn]),
                categoryColumn == -1 ? -1 : WEAPON_CATEGORIES.indexOf(row[categoryColumn].trim().toLowerCase())));
        }
    }

    private static String code(final String[] row) {
        return row[0].trim().toLowerCase();
    }

    private static boolean isTrue(final String value) {
        return value.trim().equalsIgnoreCase("true");
    }

}
