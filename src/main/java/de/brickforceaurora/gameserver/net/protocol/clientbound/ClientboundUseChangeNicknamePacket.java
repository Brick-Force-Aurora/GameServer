package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundUseChangeNicknamePacket implements IClientboundPacket {

    private int val;
    private String val2;
    private String Unnamed0;

    public ClientboundUseChangeNicknamePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundUseChangeNicknamePacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundUseChangeNicknamePacket Unnamed0(final String Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public String Unnamed0() {
        return this.Unnamed0;
    }

    @Override
    public int packetId() {
        return 502;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        if (this.val2.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.Unnamed0.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.Unnamed0.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
    }
}