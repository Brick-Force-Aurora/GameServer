package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundCacheBrickDonePacket implements IClientboundPacket {

    private int mapIndex;
    private int skyboxIndex;

    public ClientboundCacheBrickDonePacket mapIndex(final int mapIndex) {
        this.mapIndex = mapIndex;
        return this;
    }

    public int mapIndex() {
        return this.mapIndex;
    }

    public ClientboundCacheBrickDonePacket skyboxIndex(final int skyboxIndex) {
        this.skyboxIndex = skyboxIndex;
        return this;
    }

    public int skyboxIndex() {
        return this.skyboxIndex;
    }

    @Override
    public int packetId() {
        return 22;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.mapIndex);
        buf.writeInt(this.skyboxIndex);
    }
}
