package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundQuickJoinPacket implements IServerboundPacket {

    private int qjModeMask;
    private int qjOfficialMask;

    public ServerboundQuickJoinPacket qjModeMask(final int qjModeMask) {
        this.qjModeMask = qjModeMask;
        return this;
    }

    public int qjModeMask() {
        return this.qjModeMask;
    }

    public ServerboundQuickJoinPacket qjOfficialMask(final int qjOfficialMask) {
        this.qjOfficialMask = qjOfficialMask;
        return this;
    }

    public int qjOfficialMask() {
        return this.qjOfficialMask;
    }

    @Override
    public int packetId() {
        return 9;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.qjModeMask = buf.readInt();
        this.qjOfficialMask = buf.readInt();
    }
}