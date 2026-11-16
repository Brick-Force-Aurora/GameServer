package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundFollowingPacket implements IServerboundPacket {

    private int followeeSeq;
    private String nickName;

    public ServerboundFollowingPacket followeeSeq(final int followeeSeq) {
        this.followeeSeq = followeeSeq;
        return this;
    }

    public int followeeSeq() {
        return this.followeeSeq;
    }

    public ServerboundFollowingPacket nickName(final String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String nickName() {
        return this.nickName;
    }

    @Override
    public int packetId() {
        return 484;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.followeeSeq = buf.readInt();
        this.nickName = buf.readString();
    }
}