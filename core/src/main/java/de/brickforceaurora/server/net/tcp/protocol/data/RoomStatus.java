package de.brickforceaurora.server.net.tcp.protocol.data;

public enum RoomStatus {

    WAITING(0),
    PENDING(1),
    PLAYING(2),
    MATCHING(3),
    MATCH_END(4);

    public static final RoomStatus[] VALUES = RoomStatus.values();

    public static RoomStatus byId(final int id) {
        if (id < 0 || id >= VALUES.length) {
            return null;
        }
        return VALUES[id];
    }

    private final int id;

    RoomStatus(final int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

}
