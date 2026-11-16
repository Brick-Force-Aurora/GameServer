package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSavePlayerCommonOptPacket implements IServerboundPacket {

    private int commonOpt;

    public ServerboundSavePlayerCommonOptPacket commonOpt(final int commonOpt) {
        this.commonOpt = commonOpt;
        return this;
    }

    public int commonOpt() {
        return this.commonOpt;
    }

    @Override
    public int packetId() {
        return 460;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.commonOpt = buf.readInt();
    }
}