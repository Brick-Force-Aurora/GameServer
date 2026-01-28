package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundLevelupRewardPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private String val3;
    private int val4;
    private int val5;

    public ClientboundLevelupRewardPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundLevelupRewardPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundLevelupRewardPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundLevelupRewardPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundLevelupRewardPacket val5(final int val5) {
        if (val5 > 255L || val5 < 0L) {
            throw new IllegalArgumentException("Value " + val5 + " is out of bounds of allowed number range of 0 - 255");
        }
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    @Override
    public int packetId() {
        return 489;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        if (this.val3.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val4);
        buffer.writeByte(this.val5 & 0xFF);
    }
}