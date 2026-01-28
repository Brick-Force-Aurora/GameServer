package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundInvitePacket implements IServerboundPacket {

    private int inviteeSeq;
    private String nickName;

    public ServerboundInvitePacket inviteeSeq(final int inviteeSeq) {
        this.inviteeSeq = inviteeSeq;
        return this;
    }

    public int inviteeSeq() {
        return this.inviteeSeq;
    }

    public ServerboundInvitePacket nickName(final String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String nickName() {
        return this.nickName;
    }

    @Override
    public int packetId() {
        return 481;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.inviteeSeq = buffer.readIntLE();
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