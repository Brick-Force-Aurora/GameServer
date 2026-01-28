package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundUpgradeItemPacket implements IClientboundPacket {

    private int val;
    private long Unnamed0;
    private long Unnamed1;
    private int val4;
    private int val5;

    public ClientboundUpgradeItemPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundUpgradeItemPacket Unnamed0(final long Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public long Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundUpgradeItemPacket Unnamed1(final long Unnamed1) {
        this.Unnamed1 = Unnamed1;
        return this;
    }

    public long Unnamed1() {
        return this.Unnamed1;
    }

    public ClientboundUpgradeItemPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundUpgradeItemPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    @Override
    public int packetId() {
        return 354;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeLongLE(this.Unnamed0);
        buffer.writeLongLE(this.Unnamed1);
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
    }
}