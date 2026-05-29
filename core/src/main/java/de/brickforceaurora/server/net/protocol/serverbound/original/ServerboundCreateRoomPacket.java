package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCreateRoomPacket implements IServerboundPacket {

    private int type;
    private String title;
    private boolean isLocked;
    private String password;
    private int maxPlayers;
    private int[] parameters;
    private String alias;
    private int master;

    public final ServerboundCreateRoomPacket type(int type) {
        this.type = type;
        return this;
    }

    public final int type() {
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

    public final ServerboundCreateRoomPacket master(int master) {
        this.master = master;
        return this;
    }

    public final int master() {
        return this.master;
    }

    @Override
    public int packetId() {
        return 7;
    }

    @Override
    public final void read(PacketBuf buf) {
        this.type = buf.readInt();
        this.title = buf.readString();
        this.isLocked = buf.readBoolean();
        this.password = buf.readString();
        this.maxPlayers = buf.readInt();
        for (int i = 0; i < 8; i++){
            this.parameters[i] = buf.readInt();
        }
        this.alias = buf.readString();
        this.master = buf.readInt();
    }

}
