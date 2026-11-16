package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCtfPickFlagPacket implements IServerboundPacket {

    private int flag;

    public ServerboundCtfPickFlagPacket flag(final int flag) {
        this.flag = flag;
        return this;
    }

    public int flag() {
        return this.flag;
    }

    @Override
    public int packetId() {
        return 285;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.flag = buf.readInt();
    }
}