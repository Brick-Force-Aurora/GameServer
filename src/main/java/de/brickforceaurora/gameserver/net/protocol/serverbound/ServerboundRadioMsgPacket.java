package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRadioMsgPacket implements IServerboundPacket {

    private int category;
    private int msg;

    public ServerboundRadioMsgPacket category(final int category) {
        this.category = category;
        return this;
    }

    public int category() {
        return this.category;
    }

    public ServerboundRadioMsgPacket msg(final int msg) {
        this.msg = msg;
        return this;
    }

    public int msg() {
        return this.msg;
    }

    @Override
    public int packetId() {
        return 95;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.category = buffer.readIntLE();
        this.msg = buffer.readIntLE();
    }
}