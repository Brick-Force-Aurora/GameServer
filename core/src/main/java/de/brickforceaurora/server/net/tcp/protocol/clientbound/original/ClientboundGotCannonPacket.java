package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundGotCannonPacket implements IClientboundPacket {

    private int shooter;
    private int cannon;

    public ClientboundGotCannonPacket shooter(final int shooter) {
        this.shooter = shooter;
        return this;
    }

    public int shooter() {
        return this.shooter;
    }

    public ClientboundGotCannonPacket cannon(final int cannon) {
        this.cannon = cannon;
        return this;
    }

    public int cannon() {
        return this.cannon;
    }

    @Override
    public int packetId() {
        return 163;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.shooter);
        buf.writeInt(this.cannon);
    }
}
