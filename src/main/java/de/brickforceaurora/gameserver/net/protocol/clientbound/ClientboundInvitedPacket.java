package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundInvitedPacket implements IClientboundPacket {

    private int invitorSeq;
    private String invitorNickname;
    private int channelIndex;
    private int roomNo;
    private int mode;
    private String pswd;
    private int clanSeq;
    private int squadIndex;
    private int squadCounterIndex;

    public ClientboundInvitedPacket invitorSeq(final int invitorSeq) {
        this.invitorSeq = invitorSeq;
        return this;
    }

    public int invitorSeq() {
        return this.invitorSeq;
    }

    public ClientboundInvitedPacket invitorNickname(final String invitorNickname) {
        this.invitorNickname = invitorNickname;
        return this;
    }

    public String invitorNickname() {
        return this.invitorNickname;
    }

    public ClientboundInvitedPacket channelIndex(final int channelIndex) {
        this.channelIndex = channelIndex;
        return this;
    }

    public int channelIndex() {
        return this.channelIndex;
    }

    public ClientboundInvitedPacket roomNo(final int roomNo) {
        this.roomNo = roomNo;
        return this;
    }

    public int roomNo() {
        return this.roomNo;
    }

    public ClientboundInvitedPacket mode(final int mode) {
        this.mode = mode;
        return this;
    }

    public int mode() {
        return this.mode;
    }

    public ClientboundInvitedPacket pswd(final String pswd) {
        this.pswd = pswd;
        return this;
    }

    public String pswd() {
        return this.pswd;
    }

    public ClientboundInvitedPacket clanSeq(final int clanSeq) {
        this.clanSeq = clanSeq;
        return this;
    }

    public int clanSeq() {
        return this.clanSeq;
    }

    public ClientboundInvitedPacket squadIndex(final int squadIndex) {
        this.squadIndex = squadIndex;
        return this;
    }

    public int squadIndex() {
        return this.squadIndex;
    }

    public ClientboundInvitedPacket squadCounterIndex(final int squadCounterIndex) {
        this.squadCounterIndex = squadCounterIndex;
        return this;
    }

    public int squadCounterIndex() {
        return this.squadCounterIndex;
    }

    @Override
    public int packetId() {
        return 483;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.invitorSeq);
        if (this.invitorNickname.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.invitorNickname.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.channelIndex);
        buffer.writeIntLE(this.roomNo);
        buffer.writeIntLE(this.mode);
        if (this.pswd.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.pswd.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.clanSeq);
        buffer.writeIntLE(this.squadIndex);
        buffer.writeIntLE(this.squadCounterIndex);
    }
}