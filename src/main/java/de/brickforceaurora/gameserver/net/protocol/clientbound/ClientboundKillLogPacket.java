package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundKillLogPacket implements IClientboundPacket {

    private byte val;
    private int val2;
    private byte val3;
    private int val4;
    private int val5;
    private int val6;

    public ClientboundKillLogPacket val(final byte val) {
        this.val = val;
        return this;
    }

    public byte val() {
        return this.val;
    }

    public ClientboundKillLogPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundKillLogPacket val3(final byte val3) {
        this.val3 = val3;
        return this;
    }

    public byte val3() {
        return this.val3;
    }

    public ClientboundKillLogPacket val4(final int val4) {
        this.val4 = val4;
        return this;
    }

    public int val4() {
        return this.val4;
    }

    public ClientboundKillLogPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundKillLogPacket val6(final int val6) {
        this.val6 = val6;
        return this;
    }

    public int val6() {
        return this.val6;
    }

    @Override
    public int packetId() {
        return 45;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeByte(this.val);
        buffer.writeIntLE(this.val2);
        buffer.writeByte(this.val3);
        buffer.writeIntLE(this.val4);
        buffer.writeIntLE(this.val5);
        buffer.writeIntLE(this.val6);
    }
}