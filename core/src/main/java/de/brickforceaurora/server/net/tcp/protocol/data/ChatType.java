package de.brickforceaurora.server.net.tcp.protocol.data;

public enum ChatType {

    SYSTEM((byte) 2),
    NORMAL((byte) 0),
    TEAM((byte) 4),
    CLAN((byte) 3),
    WHISPER((byte) 1),
    TREASURE((byte) 5);

    public static final ChatType[] VALUES = ChatType.values();

    public static ChatType byId(final byte id) {
        if (id < 0 || id >= VALUES.length) {
            return null;
        }
        return VALUES[id];
    }

    private final byte id;

    ChatType(final byte id) {
        this.id = id;
    }

    public byte id() {
        return id;
    }

}
