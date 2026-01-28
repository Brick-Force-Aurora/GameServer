package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundOpenRandomBoxPacket implements IClientboundPacket {

    private long val;
    private String val2;
    private int val3;

    public ClientboundOpenRandomBoxPacket val(final long val) {
        this.val = val;
        return this;
    }

    public long val() {
        return this.val;
    }

    public ClientboundOpenRandomBoxPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundOpenRandomBoxPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    @Override
    public int packetId() {
        return 220;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeLongLE(this.val);
        if (this.val2.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val3);
    }
}