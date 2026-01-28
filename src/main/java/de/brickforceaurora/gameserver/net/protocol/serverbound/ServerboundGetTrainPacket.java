package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundGetTrainPacket implements IServerboundPacket {

    private int trainSeq;
    private int trainID;

    public ServerboundGetTrainPacket trainSeq(final int trainSeq) {
        this.trainSeq = trainSeq;
        return this;
    }

    public int trainSeq() {
        return this.trainSeq;
    }

    public ServerboundGetTrainPacket trainID(final int trainID) {
        this.trainID = trainID;
        return this;
    }

    public int trainID() {
        return this.trainID;
    }

    @Override
    public int packetId() {
        return 551;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.trainSeq = buffer.readIntLE();
        this.trainID = buffer.readIntLE();
    }
}