package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.IsActive = buffer.readBoolean();
    }
}