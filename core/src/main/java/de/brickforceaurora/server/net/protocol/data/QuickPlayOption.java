package de.brickforceaurora.server.net.protocol.data;

public enum QuickPlayOption {

    ALL_MAPS(0),
    OFFICIAL_MAPS(1),
    USER_MADE_MAPS(2);

    public static final QuickPlayOption[] VALUES = QuickPlayOption.values();

    public static QuickPlayOption byId(int id) {
        if (id < 0 || id > 2) {
            return null;
        }
        return VALUES[id];
    }

    private final int id;

    private QuickPlayOption(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

}
