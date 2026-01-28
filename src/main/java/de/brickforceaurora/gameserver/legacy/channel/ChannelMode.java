package de.brickforceaurora.gameserver.legacy.channel;

public enum ChannelMode {
    NEWBIE(1),
    BATTLE(2),
    MAP_EDIT(3),
    CLAN(4);

    public final int id;

    ChannelMode(int id) {
        this.id = id;
    }

    public static ChannelMode fromId(int id) {
        for (ChannelMode m : values()) {
            if (m.id == id) return m;
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
