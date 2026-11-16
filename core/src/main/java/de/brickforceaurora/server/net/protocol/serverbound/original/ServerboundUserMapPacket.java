package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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