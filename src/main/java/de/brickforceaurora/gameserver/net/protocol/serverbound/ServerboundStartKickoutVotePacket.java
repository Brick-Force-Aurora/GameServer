package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.target = buffer.readIntLE();
        this.reason = buffer.readIntLE();
    }
}