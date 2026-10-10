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

    public static ClientStatus byId(int id) {
        for (ClientStatus status : VALUES) {
            if (status.id == id) {
                return status;
            }
        }
        return null;
    }

    private final int id;

    private ClientStatus(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

}