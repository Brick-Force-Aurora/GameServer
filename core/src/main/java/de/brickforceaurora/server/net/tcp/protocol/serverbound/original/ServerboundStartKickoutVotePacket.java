package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundStartKickoutVotePacket implements IServerboundPacket {

    private int target;
    private int reason;

    public ServerboundStartKickoutVotePacket target(final int target) {
        this.target = target;
        return this;
    }

    public int target() {
        return this.target;
    }

    public ServerboundStartKickoutVotePacket reason(final int reason) {
        this.reason = reason;
        return this;
    }

    public int reason() {
        return this.reason;
    }

    @Override
    public int packetId() {
        return 494;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.target = buf.readInt();
        this.reason = buf.readInt();
    }
}