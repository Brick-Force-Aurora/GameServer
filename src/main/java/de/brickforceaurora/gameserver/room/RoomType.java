package de.brickforceaurora.gameserver.room;

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

    RoomType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
