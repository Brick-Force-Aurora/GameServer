package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundLoadCompletePacket implements IServerboundPacket {

    private int crc;

    public ServerboundLoadCompletePacket crc(final int crc) {
        this.crc = crc;
        return this;
    }

    public int crc() {
        return this.crc;
    }

    @Override
    public int packetId() {
        return 42;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.crc = buf.readInt();
    }
}