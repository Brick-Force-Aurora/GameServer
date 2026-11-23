package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundOpenDoorPacket implements IClientboundPacket {

    private int door;

    public ClientboundOpenDoorPacket door(final int door) {
        this.door = door;
        return this;
    }

    public int door() {
        return this.door;
    }

    @Override
    public int packetId() {
        return 450;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.door);
    }
}
