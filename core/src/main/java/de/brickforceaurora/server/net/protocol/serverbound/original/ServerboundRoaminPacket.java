package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRoaminPacket implements IServerboundPacket {

    private int clientId;
    private int userType;
    private boolean isWebPlayer;
    private int language;
    private String hash;

    public ServerboundRoaminPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundRoaminPacket userType(final int userType) {
        this.userType = userType;
        return this;
    }

    public int userType() {
        return this.userType;
    }

    public ServerboundRoaminPacket isWebPlayer(final boolean isWebPlayer) {
        this.isWebPlayer = isWebPlayer;
        return this;
    }

    public boolean isWebPlayer() {
        return this.isWebPlayer;
    }

    public ServerboundRoaminPacket language(final int language) {
        this.language = language;
        return this;
    }

    public int language() {
        return this.language;
    }

    public ServerboundRoaminPacket hash(final String hash) {
        this.hash = hash;
        return this;
    }

    public String hash() {
        return this.hash;
    }

    @Override
    public int packetId() {
        return 145;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
        this.userType = buf.readInt();
        this.isWebPlayer = buf.readBoolean();
        this.language = buf.readInt();
        this.hash = buf.readString();
    }
}