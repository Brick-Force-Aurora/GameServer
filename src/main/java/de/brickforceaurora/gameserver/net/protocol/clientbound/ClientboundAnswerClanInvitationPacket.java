package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundAnswerClanInvitationPacket implements IClientboundPacket {

    private int val;
    private long val2;
    private int Unnamed0;
    private boolean val4;

    public ClientboundAnswerClanInvitationPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundAnswerClanInvitationPacket val2(final long val2) {
        this.val2 = val2;
        return this;
    }

    public long val2() {
        return this.val2;
    }

    public ClientboundAnswerClanInvitationPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundAnswerClanInvitationPacket val4(final boolean val4) {
        this.val4 = val4;
        return this;
    }

    public boolean val4() {
        return this.val4;
    }

    @Override
    public int packetId() {
        return 196;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeLongLE(this.val2);
        buffer.writeIntLE(this.Unnamed0);
        buffer.writeBoolean(this.val4);
    }
}