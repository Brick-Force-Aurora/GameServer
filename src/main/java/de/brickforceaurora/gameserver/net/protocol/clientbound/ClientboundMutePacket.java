package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMutePacket implements IClientboundPacket {

    private String val;
    private int val2;

    public ClientboundMutePacket val(final String val) {
        this.val = val;
        return this;
    }

    public String val() {
        return this.val;
    }

    public ClientboundMutePacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 456;
    }

    @Override
    public void write(final ByteBuf buffer) {
        if (this.val.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val2);
    }
}