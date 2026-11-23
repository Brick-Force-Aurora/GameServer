package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.trainSeq = buf.readInt();
    }
}