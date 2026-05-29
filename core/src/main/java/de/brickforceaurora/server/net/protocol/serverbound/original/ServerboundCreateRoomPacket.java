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
    private int roomOwnerId;

    public final ServerboundCreateRoomPacket type(RoomType type) {
        this.type = type;
        return this;
    }

    public final RoomType type() {
        return this.type;
    }

    public final ServerboundCreateRoomPacket title(String title) {
        this.title = title;
        return this;
    }

    public final String title() {
        return this.title;
    }

    public final ServerboundCreateRoomPacket type(boolean isLocked) {
        this.isLocked = isLocked;
        return this;
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final ServerboundCreateRoomPacket password(String password) {
        this.password = password;
        return this;
    }

    public final String password() {
        return this.password;
    }

    public final ServerboundCreateRoomPacket maxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public final int maxPlayers() {
        return this.maxPlayers;
    }

    public final ServerboundCreateRoomPacket parameters(int[] parameters) {
        this.parameters = parameters;
        return this;
    }

    public final int[] parameters() {
        return this.parameters;
    }

    public final ServerboundCreateRoomPacket alias(String alias) {
        this.alias = alias;
        return this;
    }

    public final String alias() {
        return this.alias;
    }

    public final ServerboundCreateRoomPacket roomOwnerId(int roomOwnerId) {
        this.roomOwnerId = roomOwnerId;
        return this;
    }

    public final int roomOwnerId() {
        return this.roomOwnerId;
    }

    @Override
    public int packetId() {
        return 7;
    }

    @Override
    public final void read(PacketBuf buf) {
        this.type = RoomType.byId(buf.readInt());
        this.title = buf.readString();
        this.isLocked = buf.readBoolean();
        this.password = buf.readString();
        this.maxPlayers = buf.readInt();
        for (int i = 0; i < 8; i++) {
            this.parameters[i] = buf.readInt();
        }
        this.alias = buf.readString();
        this.roomOwnerId = buf.readInt();
    }

}
