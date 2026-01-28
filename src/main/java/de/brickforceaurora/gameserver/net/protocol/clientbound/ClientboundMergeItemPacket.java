package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMergeItemPacket implements IClientboundPacket {

    private int val;
    private long val2;
    private long val3;
    private int val4;

    public ClientboundMergeItemPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundMergeItemPacket val2(final long val2) {
        this.val2 = val2;
        return this;
    }

    public long val2() {
        return this.val2;
    }

    public ClientboundMergeItemPacket val3(final long val3) {
        this.val3 = val3;
        return this;
    }

    public long val3() {
        return this.val3;
    }

    public ClientboundMergeItemPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 358;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeLongLE(this.val2);
        buffer.writeLongLE(this.val3);
        buffer.writeIntLE(this.val4);
    }
}