package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCloseDoorPacket implements IServerboundPacket {

    private int clientId;

    public ServerboundCloseDoorPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    @Override
    public int packetId() {
        return 448;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
    }
}