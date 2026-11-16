package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundBuyItemPacket implements IServerboundPacket {

    private String code;
    private int buyHow;
    private int option;
    private int val;
    private boolean needEqup;

    public ServerboundBuyItemPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    public ServerboundBuyItemPacket buyHow(final int buyHow) {
        this.buyHow = buyHow;
        return this;
    }

    public int buyHow() {
        return this.buyHow;
    }

    public ServerboundBuyItemPacket option(final int option) {
        this.option = option;
        return this;
    }

    public int option() {
        return this.option;
    }

    public ServerboundBuyItemPacket val(final int val) {
        if (val > 255L || val < 0L) {
            throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ServerboundBuyItemPacket needEqup(final boolean needEqup) {
        this.needEqup = needEqup;
        return this;
    }

    public boolean needEqup() {
        return this.needEqup;
    }

    @Override
    public int packetId() {
        return 121;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.code = buf.readString();
        this.buyHow = buf.readInt();
        this.option = buf.readInt();
        this.val = buf.readUnsignedByte();
        this.needEqup = buf.readBoolean();
    }
}