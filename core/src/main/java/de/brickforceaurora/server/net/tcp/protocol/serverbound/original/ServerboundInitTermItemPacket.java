package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundInitTermItemPacket implements IServerboundPacket {

    private long item;
    private String code;

    public ServerboundInitTermItemPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundInitTermItemPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    @Override
    public int packetId() {
        return 307;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.item = buf.readLong();
        this.code = buf.readString();
    }
}