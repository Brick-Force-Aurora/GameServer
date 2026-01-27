package de.brickforceaurora.gameserver.channel;

public enum ClientStatus {
    INVALID(-1),
    LOBBY(0),
    ROOM(1),
    MATCH(2);

    private final int id;

    ClientStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
