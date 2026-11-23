package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundUserMapPacket implements IServerboundPacket {

    private int page;

    public ServerboundUserMapPacket page(final int page) {
        this.page = page;
        return this;
    }

    public int page() {
        return this.page;
    }

    @Override
    public int packetId() {
        return 429;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.page = buf.readInt();
    }
}