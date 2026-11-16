package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundJoinPacket implements IServerboundPacket {

    private int roomId;
    private String password;
    private boolean wasInvited;

    public ServerboundJoinPacket roomId(final int roomId) {
        this.roomId = roomId;
        return this;
    }

    public int roomId() {
        return this.roomId;
    }

    public ServerboundJoinPacket password(final String password) {
        this.password = password;
        return this;
    }

    public String password() {
        return this.password;
    }

    public ServerboundJoinPacket wasInvited(final boolean wasInvited) {
        this.wasInvited = wasInvited;
        return this;
    }

    public boolean wasInvited() {
        return this.wasInvited;
    }

    @Override
    public int packetId() {
        return 28;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.roomId = buf.readInt();
        this.password = buf.readString();
        this.wasInvited = buf.readBoolean();
    }
}