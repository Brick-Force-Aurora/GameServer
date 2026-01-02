package de.brickforceaurora.gameserver.room;

import de.brickforceaurora.gameserver.item.UpgradeCategory;

import java.util.HashMap;
import java.util.Map;

public enum RoomType {
    NONE(-1),
    MAP_EDITOR(0),
    TEAM_MATCH(1),
    INDIVIDUAL(2),
    CAPTURE_THE_FLAG(3),
    EXPLOSION(4),
    MISSION(5),
    BND(6),
    BUNGEE(7),
    ESCAPE(8),
    ZOMBIE(9),
    NUM_TYPE(10);

    private final int id;
    private static final Map<Integer, RoomType> BY_VALUE = new HashMap<>();

    static {
        for (RoomType c : values()) {
            BY_VALUE.put(c.id, c);
        }
    }

    RoomType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RoomType fromValue(int v) {
        return BY_VALUE.getOrDefault(v, NONE);
    }

}
