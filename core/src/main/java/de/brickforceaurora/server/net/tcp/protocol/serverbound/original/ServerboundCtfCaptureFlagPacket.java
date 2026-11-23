package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.flag = buf.readInt();
        this.opponent = buf.readBoolean();
    }
}