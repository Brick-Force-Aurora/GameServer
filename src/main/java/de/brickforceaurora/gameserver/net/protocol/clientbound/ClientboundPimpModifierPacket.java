package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundPimpModifierPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private float val3;

    public ClientboundPimpModifierPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundPimpModifierPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundPimpModifierPacket val3(final float val3) {
        this.val3 = val3;
        return this;
    }

    public float val3() {
        return this.val3;
    }

    @Override
    public int packetId() {
        return 356;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeFloatLE(this.val3);
    }
}