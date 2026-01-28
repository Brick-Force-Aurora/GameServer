package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundSavePlayerCommonOptPacket implements IServerboundPacket {

    private int commonOpt;

    public ServerboundSavePlayerCommonOptPacket commonOpt(final int commonOpt) {
        this.commonOpt = commonOpt;
        return this;
    }

    public int commonOpt() {
        return this.commonOpt;
    }

    @Override
    public int packetId() {
        return 460;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.commonOpt = buffer.readIntLE();
    }
}