package de.brickforceaurora.server.net.protocol.data;

public enum RoomType {

    MAP_EDITOR(0, "Build Mode"),
    TEAM_MATCH(1, "Team Deathmatch"),
    INDIVIDUAL(2, "Deathmatch"),
    CAPTURE_THE_FLAG(3, "Capture The Flag"),
    EXPLOSION(4, "Demolition"),
    MISSION(5, "Bee Defense"),
    BND(6, "Build and Destroy"),
    BUNGEE(7, "Freefall"),
    ESCAPE(8, "Run"),
    ZOMBIE(9, "Zombie");

    public static final RoomType[] VALUES = RoomType.values();

    public static RoomType byId(int id) {
        if (id < 0 || id >= VALUES.length) {
            return null;
        }
        return VALUES[id];
    }

    private final int id;
    private final String modeName;
    
    private RoomType(int id, String modeName) {
        this.id = id;
        this.modeName = modeName;
    }

    public int id() {
        return id;
    }

    public String modeName() {
        return modeName;
    }

}
