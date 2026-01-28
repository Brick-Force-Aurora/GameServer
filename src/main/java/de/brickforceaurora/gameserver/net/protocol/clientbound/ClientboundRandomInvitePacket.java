package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundRandomInvitePacket implements IClientboundPacket {

    private int val;
    private int Unnamed0;
    private String val3;

    public ClientboundRandomInvitePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundRandomInvitePacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundRandomInvitePacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    @Override
    public int packetId() {
        return 518;
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
    }
}