package de.brickforceaurora.server.net.protocol.data;

public enum ChannelMode {

    NEWBIE(1),
    BATTLE(2),
    MAPEDIT(3),
    CLAN(4);

    public static final ChannelMode[] VALUES = ChannelMode.values();

    public static ChannelMode byId(final int id) {
        if (id < 1 || id > 4) {
            return null;
        }
        return VALUES[id - 1];
    }

    private final int id;

    ChannelMode(final int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

}
