package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundBmUninstallBombPacket implements IServerboundPacket {

    private int bomb;

    public ServerboundBmUninstallBombPacket bomb(final int bomb) {
        this.bomb = bomb;
        return this;
    }

    public int bomb() {
        return this.bomb;
    }

    @Override
    public int packetId() {
        return 281;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.bomb = buf.readInt();
    }
}