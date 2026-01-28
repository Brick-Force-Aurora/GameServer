package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundOpenRandomBoxPacket implements IServerboundPacket {

    private int randombox;

    public ServerboundOpenRandomBoxPacket randombox(final int randombox) {
        this.randombox = randombox;
        return this;
    }

    public int randombox() {
        return this.randombox;
    }

    @Override
    public int packetId() {
        return 219;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.randombox = buffer.readIntLE();
    }
}