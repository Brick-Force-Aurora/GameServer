package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.inviteeSeq = buf.readInt();
        this.nickName = buf.readString();
    }
}