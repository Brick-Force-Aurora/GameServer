package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundBmBlastPacket implements IServerboundPacket {

    private int bomb;

    public ServerboundBmBlastPacket bomb(final int bomb) {
        this.bomb = bomb;
        return this;
    }

    public int bomb() {
        return this.bomb;
    }

    @Override
    public int packetId() {
        return 283;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.bomb = buf.readInt();
    }
}