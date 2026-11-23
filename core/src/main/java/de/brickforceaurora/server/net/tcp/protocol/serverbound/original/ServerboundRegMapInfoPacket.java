package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundRegMapInfoPacket implements IServerboundPacket {

    private int map;

    public ServerboundRegMapInfoPacket map(final int map) {
        this.map = map;
        return this;
    }

    public int map() {
        return this.map;
    }

    @Override
    public int packetId() {
        return 337;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.map = buf.readInt();
    }
}