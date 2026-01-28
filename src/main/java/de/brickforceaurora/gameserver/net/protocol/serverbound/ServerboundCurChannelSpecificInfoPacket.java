package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCurChannelSpecificInfoPacket implements IServerboundPacket {

    private int val;

    public ServerboundCurChannelSpecificInfoPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 477;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.val = buffer.readIntLE();
    }
}