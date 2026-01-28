package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.qjModeMask = buffer.readIntLE();
        this.qjOfficialMask = buffer.readIntLE();
    }
}