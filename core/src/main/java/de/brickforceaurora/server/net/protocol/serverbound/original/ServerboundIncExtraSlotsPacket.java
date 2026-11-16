package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundIncExtraSlotsPacket implements IServerboundPacket {

    private long item;
    private String itemCode;

    public ServerboundIncExtraSlotsPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundIncExtraSlotsPacket itemCode(final String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String itemCode() {
        return this.itemCode;
    }

    @Override
    public int packetId() {
        return 407;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.item = buf.readLong();
        this.itemCode = buf.readString();
    }
}