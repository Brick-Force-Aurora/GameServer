package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundRoomConfigFailPacket implements IClientboundPacket {

    private int Unnamed0;

    public ClientboundRoomConfigFailPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    @Override
    public int packetId() {
        return 453;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.Unnamed0);
    }
}