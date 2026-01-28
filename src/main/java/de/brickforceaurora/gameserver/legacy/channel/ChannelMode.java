package de.brickforceaurora.gameserver.legacy.channel;

public enum ChannelMode {
    NEWBIE(1),
    BATTLE(2),
    MAP_EDIT(3),
    CLAN(4);

    public final int id;

    ChannelMode(final int id) {
        this.id = id;
    }

    public static ChannelMode fromId(final int id) {
        for (final ChannelMode m : values()) {
            if (m.id == id) {
                return m;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
