package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundGadgetActionPacket implements IClientboundPacket {

    private int Unnamed0;
    private int Unnamed1;

    public ClientboundGadgetActionPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundGadgetActionPacket Unnamed1(final int Unnamed1) {
        this.Unnamed1 = Unnamed1;
        return this;
    }

    public int Unnamed1() {
        return this.Unnamed1;
    }

    @Override
    public int packetId() {
        return 403;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.Unnamed0);
        buffer.writeIntLE(this.Unnamed1);
    }
}