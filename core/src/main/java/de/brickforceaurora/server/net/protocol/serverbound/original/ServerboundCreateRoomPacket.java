package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.RoomType;

public final class ServerboundCreateRoomPacket implements IServerboundPacket {

    private RoomType type;
    private String title;
    private boolean isLocked;
    private String password;
    private int maxPlayers;
    private int[] parameters;
    private String alias;

    public ServerboundCreateRoomPacket type(final RoomType type) {
        this.type = type;
        return this;
    }

    public RoomType type() {
        return this.type;
    }

    public ServerboundCreateRoomPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundCreateRoomPacket type(final boolean isLocked) {
        this.isLocked = isLocked;
        return this;
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    public ServerboundCreateRoomPacket password(final String password) {
        this.password = password;
        return this;
    }

    public String password() {
        return this.password;
    }

    public ServerboundCreateRoomPacket maxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public int maxPlayers() {
        return this.maxPlayers;
    }

    public ServerboundCreateRoomPacket parameters(final int[] parameters) {
        this.parameters = parameters;
        return this;
    }

    public int[] parameters() {
        return this.parameters;
    }

    public ServerboundCreateRoomPacket alias(final String alias) {
        this.alias = alias;
        return this;
    }

    public String alias() {
        return this.alias;
    }

    @Override
    public int packetId() {
        return 7;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.type = RoomType.byId(buf.readInt());
        this.title = buf.readString();
        this.isLocked = buf.readBoolean();
        this.password = buf.readString();
        this.maxPlayers = buf.readInt();
        this.parameters = new int[8];
        for (int i = 0; i < 8; i++) {
            this.parameters[i] = buf.readInt();
        }
        this.alias = buf.readString();
    }

}
