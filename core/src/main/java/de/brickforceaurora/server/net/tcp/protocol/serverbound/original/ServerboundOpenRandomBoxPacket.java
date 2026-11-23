package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundOpenRandomBoxPacket implements IServerboundPacket {

    private int randombox;

    public ServerboundOpenRandomBoxPacket randombox(final int randombox) {
        this.randombox = randombox;
        return this;
    }

    public int randombox() {
        return this.randombox;
    }

    @Override
    public int packetId() {
        return 219;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.randombox = buf.readInt();
    }
}