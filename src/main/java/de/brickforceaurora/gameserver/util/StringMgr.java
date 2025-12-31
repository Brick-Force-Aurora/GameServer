package de.brickforceaurora.gameserver.util;

import java.util.HashMap;
import java.util.Map;

public final class StringMgr {

    private static StringMgr instance;

    public static StringMgr getInstance() {
        if (instance == null) {
            instance = new StringMgr();
        }
        return instance;
    }

    private final Map<String, String> strings;

    private StringMgr() {
        // Add any keys your code expects
        strings = new HashMap<>();
        strings.put("INFINITE", "Infinite");
        strings.put("TIMES_UNIT", "times");
        strings.put("DAYS", "days");
    }

    public String get(String key) {
        if (key == null) {
            return "";
        }

        String value = strings.get(key);
        if (value != null) {
            return value;
        }

        // Fallback: return key so you can see missing entries
        return key;
    }
}
