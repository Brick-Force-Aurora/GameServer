package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.category = buf.readInt();
        this.msg = buf.readInt();
    }
}