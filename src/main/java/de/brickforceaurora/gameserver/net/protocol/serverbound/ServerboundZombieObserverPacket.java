package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundZombieObserverPacket implements IServerboundPacket {

    private int clientId;

    public ServerboundZombieObserverPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return clientId;
    }

    @Override
    public int packetId() {
        return 549;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
    }
}