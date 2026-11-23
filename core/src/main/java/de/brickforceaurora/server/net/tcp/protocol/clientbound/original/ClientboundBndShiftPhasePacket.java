package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundBndShiftPhasePacket implements IClientboundPacket {

    private int repeat;
    private boolean isBuildPhase;

    public ClientboundBndShiftPhasePacket repeat(final int repeat) {
        this.repeat = repeat;
        return this;
    }

    public int repeat() {
        return this.repeat;
    }

    public ClientboundBndShiftPhasePacket isBuildPhase(final boolean isBuildPhase) {
        this.isBuildPhase = isBuildPhase;
        return this;
    }

    public boolean isBuildPhase() {
        return this.isBuildPhase;
    }

    @Override
    public int packetId() {
        return 344;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.repeat);
        buf.writeBoolean(this.isBuildPhase);
    }
}