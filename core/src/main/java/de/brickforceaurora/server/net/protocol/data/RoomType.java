package de.brickforceaurora.server.net.protocol.data;

public enum RoomType {

    MAP_EDITOR(0),
    TEAM_MATCH(1),
    INDIVIDUAL(2),
    CAPTURE_THE_FLAG(3),
    EXPLOSION(4),
    MISSION(5),
    BND(6),
    BUNGEE(7),
    ESCAPE(8),
    ZOMBIE(9);

    public static final RoomType[] VALUES = RoomType.values();

    public static RoomType byId(int id) {
        if (id < 0 || id >= VALUES.length) {
            return null;
        }
        return VALUES[id];
    }

    private final int id;

    private RoomType(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

}
