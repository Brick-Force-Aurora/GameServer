package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.BrickInfo;

public final class ClientboundCacheBrickPacket implements IClientboundPacket {

    private BrickInfo[] bricks = {};

    public ClientboundCacheBrickPacket bricks(final BrickInfo[] bricks) {
        this.bricks = bricks;
        return this;
    }

    public BrickInfo[] bricks() {
        return this.bricks;
    }

    @Override
    public int packetId() {
        return 21;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.bricks.length);
        for (final BrickInfo brick : this.bricks) {
            brick.toBuffer(buf);
        }
    }
}
