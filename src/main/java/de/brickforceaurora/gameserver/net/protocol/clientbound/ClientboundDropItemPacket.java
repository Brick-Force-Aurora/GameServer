package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundDropItemPacket implements IClientboundPacket {

    private int val;
    private String val2;
    private int val3;
    private int val4;
    private float val5;
    private float val6;
    private float val7;

    public ClientboundDropItemPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundDropItemPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundDropItemPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundDropItemPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundDropItemPacket val5(final float val5) {
        this.val5 = val5;
        return this;
    }

    public float val5() {
        return this.val5;
    }

    public ClientboundDropItemPacket val6(final float val6) {
        this.val6 = val6;
        return this;
    }

    public float val6() {
        return this.val6;
    }

    public ClientboundDropItemPacket val7(final float val7) {
        this.val7 = val7;
        return this;
    }

    public float val7() {
        return this.val7;
    }

    @Override
    public int packetId() {
        return 527;
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
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeFloatLE(this.val5);
        buffer.writeFloatLE(this.val6);
        buffer.writeFloatLE(this.val7);
    }
}