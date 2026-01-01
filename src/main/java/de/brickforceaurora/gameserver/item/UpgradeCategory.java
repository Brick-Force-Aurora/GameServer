package de.brickforceaurora.gameserver.item;

import java.util.HashMap;
import java.util.Map;

public enum UpgradeCategory {
    NONE(-1),
    HEAVY(0),
    ASSAULT(1),
    SNIPER(2),
    SUB_MACHINE(3),
    HAND_GUN(4),
    MELEE(5),
    GRENADE(6),
    FLASH_BANG(7),
    SMOKE(8),
    UPPER_LOWER(9),
    HELMET(10),
    OTHER(11),
    SHOTGUN(12),
    MAX(13);

    public final int value;

    private static final Map<Integer, UpgradeCategory> BY_VALUE = new HashMap<>();

    static {
        for (UpgradeCategory c : values()) {
            BY_VALUE.put(c.value, c);
        }
    }

    UpgradeCategory(int v) {
        this.value = v;
    }

    public static UpgradeCategory fromValue(int v) {
        return BY_VALUE.getOrDefault(v, NONE);
    }

}
