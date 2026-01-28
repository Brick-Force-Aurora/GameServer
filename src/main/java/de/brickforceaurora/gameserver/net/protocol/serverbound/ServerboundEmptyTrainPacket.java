package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundEmptyTrainPacket implements IServerboundPacket {

    private int trainSeq;

    public ServerboundEmptyTrainPacket trainSeq(final int trainSeq) {
        this.trainSeq = trainSeq;
        return this;
    }

    public int trainSeq() {
        return this.trainSeq;
    }

    @Override
    public int packetId() {
        return 553;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.trainSeq = buffer.readIntLE();
    }
}