package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundSlotChangePacket implements IClientboundPacket {

    private int val;
    private int Unnamed0;
    private int val3;

    public ClientboundSlotChangePacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundSlotChangePacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundSlotChangePacket val3(final int val3) {
        this.val3 = val3;
        return this;
    }

    public int val3() {
        return this.val3;
    }

    @Override
    public int packetId() {
        return 454;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.Unnamed0);
        buffer.writeIntLE(this.val3);
    }
}