package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundCloseDoorPacket implements IServerboundPacket {

    private int door;

    public ServerboundCloseDoorPacket door(final int door) {
        this.door = door;
        return this;
    }

    public int door() {
        return this.door;
    }

    @Override
    public int packetId() {
        return 448;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.door = buf.readInt();
    }
}
