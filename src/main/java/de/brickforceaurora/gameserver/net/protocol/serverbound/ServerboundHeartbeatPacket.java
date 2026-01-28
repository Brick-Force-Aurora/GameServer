package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundHeartbeatPacket implements IServerboundPacket {

    private int gmFunction;

    public ServerboundHeartbeatPacket gmFunction(final int gmFunction) {
        this.gmFunction = gmFunction;
        return this;
    }

    public int gmFunction() {
        return this.gmFunction;
    }

    @Override
    public int packetId() {
        return 3;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.gmFunction = buffer.readIntLE();
    }
}