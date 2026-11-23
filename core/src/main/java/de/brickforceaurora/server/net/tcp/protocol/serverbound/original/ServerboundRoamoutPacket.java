package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundRoamoutPacket implements IServerboundPacket {

    private int dst;

    public ServerboundRoamoutPacket dst(final int dst) {
        this.dst = dst;
        return this;
    }

    public int dst() {
        return this.dst;
    }

    @Override
    public int packetId() {
        return 143;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.dst = buf.readInt();
    }
}