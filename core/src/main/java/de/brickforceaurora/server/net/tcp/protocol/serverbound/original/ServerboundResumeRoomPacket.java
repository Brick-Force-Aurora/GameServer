package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundResumeRoomPacket implements IServerboundPacket {

    private int nextStatus;

    public ServerboundResumeRoomPacket nextStatus(final int nextStatus) {
        this.nextStatus = nextStatus;
        return this;
    }

    public int nextStatus() {
        return this.nextStatus;
    }

    @Override
    public int packetId() {
        return 32;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.nextStatus = buf.readInt();
    }
}