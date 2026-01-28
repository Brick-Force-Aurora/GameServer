package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCtfCaptureFlagPacket implements IClientboundPacket {

    private int val;
    private int Unnamed0;
    private boolean Unnamed1;

    public ClientboundCtfCaptureFlagPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundCtfCaptureFlagPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundCtfCaptureFlagPacket Unnamed1(final boolean Unnamed1) {
        this.Unnamed1 = Unnamed1;
        return this;
    }

    public boolean Unnamed1() {
        return this.Unnamed1;
    }

    @Override
    public int packetId() {
        return 288;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.val);
        buffer.writeIntLE(this.Unnamed0);
        buffer.writeBoolean(this.Unnamed1);
    }
}