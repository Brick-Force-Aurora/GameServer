package de.brickforceaurora.gameserver.room;

import java.util.HashMap;
import java.util.Map;

public enum RoomStatus {
    NONE(-1),
    WAITING(0),
    PENDING(1),
    PLAYING(2),
    MATCHING(3),
    MATCH_END(4);

    private final int id;
    private static final Map<Integer, RoomStatus> BY_VALUE = new HashMap<>();

    static {
        for (RoomStatus c : values()) {
            BY_VALUE.put(c.id, c);
        }
    }

    RoomStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RoomStatus fromValue(int v) {
        return BY_VALUE.getOrDefault(v, NONE);
    }
}
