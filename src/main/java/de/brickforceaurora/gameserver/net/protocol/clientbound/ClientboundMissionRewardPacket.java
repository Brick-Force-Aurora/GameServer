package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundMissionRewardPacket implements IClientboundPacket {

    private int val;
    private byte val2;
    private int val3;
    private int val4;

    public ClientboundMissionRewardPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundMissionRewardPacket val2(final byte val2) {
        this.val2 = val2;
        return this;
    }

    public byte val2() {
        return this.val2;
    }

    public ClientboundMissionRewardPacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    public ClientboundMissionRewardPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 490;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeByte(this.val2);
        buffer.writeIntLE(this.val3);
        buffer.writeIntLE(this.val4);
    }
}