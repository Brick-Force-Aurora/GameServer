package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.followeeSeq = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.nickName = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.nickName = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}