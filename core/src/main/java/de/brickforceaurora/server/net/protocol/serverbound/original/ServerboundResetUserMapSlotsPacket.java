package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundResetUserMapSlotsPacket implements IServerboundPacket {

    private int slot;
    private long item;
    private String itemCode;

    public ServerboundResetUserMapSlotsPacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    public ServerboundResetUserMapSlotsPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundResetUserMapSlotsPacket itemCode(final String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    public String itemCode() {
        return this.itemCode;
    }

    @Override
    public int packetId() {
        return 405;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.slot = buf.readInt();
        this.item = buf.readLong();
        this.itemCode = buf.readString();
    }
}