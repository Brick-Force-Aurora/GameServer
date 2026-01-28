package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMasterKickingPacket implements IServerboundPacket {

    private int countdown;

    public ServerboundMasterKickingPacket countdown(final int countdown) {
        this.countdown = countdown;
        return this;
    }

    public int countdown() {
        return this.countdown;
    }

    @Override
    public int packetId() {
        return 534;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.countdown = buffer.readIntLE();
    }
}