package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundEscapeActivePlayerPacket implements IServerboundPacket {

    private boolean IsActive;

    public ServerboundEscapeActivePlayerPacket IsActive(final boolean IsActive) {
        this.IsActive = IsActive;
        return this;
    }

    public boolean IsActive() {
        return this.IsActive;
    }

    @Override
    public int packetId() {
        return 525;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.IsActive = buf.readBoolean();
    }
}