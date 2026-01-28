package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundChargeForcePointPacket implements IClientboundPacket {

    private int val;
    private long Unnamed0;
    private String val3;
    private int val4;

    public ClientboundChargeForcePointPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundChargeForcePointPacket Unnamed0(final long Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public long Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundChargeForcePointPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundChargeForcePointPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 472;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeLongLE(this.Unnamed0);
        if (this.val3.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val4);
    }
}