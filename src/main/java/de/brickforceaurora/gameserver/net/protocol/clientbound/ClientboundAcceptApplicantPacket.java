package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundAcceptApplicantPacket implements IClientboundPacket {

    private int val;
    private int Unnamed0;
    private boolean val3;
    private String val4;

    public ClientboundAcceptApplicantPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundAcceptApplicantPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundAcceptApplicantPacket val3(final boolean val3) {
        this.val3 = val3;
        return this;
    }

    public boolean val3() {
        return this.val3;
    }

    public ClientboundAcceptApplicantPacket val4(final String val4) {
        this.val4 = val4;
        return this;
    }

    public String val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 218;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.Unnamed0);
        buffer.writeBoolean(this.val3);
        if (this.val4.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
    }
}