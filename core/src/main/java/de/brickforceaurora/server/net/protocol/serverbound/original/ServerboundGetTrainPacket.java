package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.trainSeq = buf.readInt();
        this.trainID = buf.readInt();
    }
}