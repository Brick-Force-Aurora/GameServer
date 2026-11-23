package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundTeamChangePacket implements IClientboundPacket {

    private int clientId;
    private int previousSlot;
    private int slot;

    public ClientboundTeamChangePacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundTeamChangePacket previousSlot(final int previousSlot) {
        this.previousSlot = previousSlot;
        return this;
    }

    public int previousSlot() {
        return this.previousSlot;
    }

    public ClientboundTeamChangePacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    @Override
    public int packetId() {
        return 81;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeInt(this.previousSlot);
        buf.writeInt(this.slot);
    }
}