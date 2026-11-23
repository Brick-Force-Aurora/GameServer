package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundGetCannonPacket implements IServerboundPacket {

    private int cannon;

    public ServerboundGetCannonPacket cannon(final int cannon) {
        this.cannon = cannon;
        return this;
    }

    public int cannon() {
        return this.cannon;
    }

    @Override
    public int packetId() {
        return 158;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.cannon = buf.readInt();
    }
}