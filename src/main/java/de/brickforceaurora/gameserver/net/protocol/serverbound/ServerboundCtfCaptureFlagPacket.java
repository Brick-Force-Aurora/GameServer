package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCtfCaptureFlagPacket implements IServerboundPacket {

    private int flag;
    private boolean opponent;

    public ServerboundCtfCaptureFlagPacket flag(final int flag) {
        this.flag = flag;
        return this;
    }

    public int flag() {
        return this.flag;
    }

    public ServerboundCtfCaptureFlagPacket opponent(final boolean opponent) {
        this.opponent = opponent;
        return this;
    }

    public boolean opponent() {
        return this.opponent;
    }

    @Override
    public int packetId() {
        return 287;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.flag = buffer.readIntLE();
        this.opponent = buffer.readBoolean();
    }
}