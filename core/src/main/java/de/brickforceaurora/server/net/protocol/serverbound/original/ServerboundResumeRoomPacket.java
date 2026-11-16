package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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