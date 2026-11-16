package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundCopyrightPacket implements IClientboundPacket {

    private int ownerClientId;
    private int userMapInfoSlot;

    public ClientboundCopyrightPacket ownerClientId(final int ownerClientId) {
        this.ownerClientId = ownerClientId;
        return this;
    }

    public int ownerClientId() {
        return this.ownerClientId;
    }

    public ClientboundCopyrightPacket userMapInfoSlot(final int userMapInfoSlot) {
        this.userMapInfoSlot = userMapInfoSlot;
        return this;
    }

    public int userMapInfoSlot() {
        return this.userMapInfoSlot;
    }

    @Override
    public int packetId() {
        return 53;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.ownerClientId);
        buf.writeInt(this.userMapInfoSlot);
    }
}