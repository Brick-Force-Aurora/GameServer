package de.brickforceaurora.gameserver.room;

public enum RoomStatus {
    NONE(-1),
    WAITING(0),
    PENDING(1),
    PLAYING(2),
    MATCHING(3),
    MATCH_END(4);

    private final int id;

    RoomStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
