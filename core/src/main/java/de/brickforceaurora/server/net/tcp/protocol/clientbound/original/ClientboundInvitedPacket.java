package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void write(final PacketBuf buf) {
        buf.writeInt(this.invitorSeq);
        buf.writeString(this.invitorNickname);
        buf.writeInt(this.channelIndex);
        buf.writeInt(this.roomNo);
        buf.writeInt(this.mode);
        buf.writeString(this.pswd);
        buf.writeInt(this.clanSeq);
        buf.writeInt(this.squadIndex);
        buf.writeInt(this.squadCounterIndex);
    }
}