package de.brickforceaurora.server.net.protocol.data;

public enum ClientStatus {

    WAITING(0),
    READY(1),
    LOADING(2),
    P2PING(3),
    PLAYING(4),
    SHOP(5),
    INVENTORY(6);

    public static final ClientStatus[] VALUES = ClientStatus.values();

    public static ClientStatus byId(final int id) {
        for (final ClientStatus status : VALUES) {
            if (status.id == id) {
                return status;
            }
        }
        return null;
    }

    private final int id;

    ClientStatus(final int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

}