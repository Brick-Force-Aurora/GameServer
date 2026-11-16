package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundTcEnterPacket implements IServerboundPacket {

    private int chest;

    public ServerboundTcEnterPacket chest(final int chest) {
        this.chest = chest;
        return this;
    }

    public int chest() {
        return this.chest;
    }

    @Override
    public int packetId() {
        return 372;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.chest = buf.readInt();
    }
}