package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundFollowingPacket implements IClientboundPacket {

    private int val;
    private int Unnamed0;
    private String val3;
    private int val4;
    private int val5;

    public ClientboundFollowingPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundFollowingPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundFollowingPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundFollowingPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundFollowingPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    @Override
    public int packetId() {
        return 485;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.Unnamed0);
        if (this.val3.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
    }
}