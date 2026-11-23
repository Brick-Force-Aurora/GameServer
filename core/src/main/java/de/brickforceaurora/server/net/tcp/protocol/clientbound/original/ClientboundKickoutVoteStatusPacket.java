package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundKickoutVoteStatusPacket implements IClientboundPacket {

    private int yes;
    private int no;
    private int total;
    private int reason;
    private int target;
    private String targetNickname;
    private boolean isVoteAble;
    private boolean isVoted;
    private int remainTime;

    public ClientboundKickoutVoteStatusPacket yes(final int yes) {
        this.yes = yes;
        return this;
    }

    public int yes() {
        return this.yes;
    }

    public ClientboundKickoutVoteStatusPacket no(final int no) {
        this.no = no;
        return this;
    }

    public int no() {
        return this.no;
    }

    public ClientboundKickoutVoteStatusPacket total(final int total) {
        this.total = total;
        return this;
    }

    public int total() {
        return this.total;
    }

    public ClientboundKickoutVoteStatusPacket reason(final int reason) {
        this.reason = reason;
        return this;
    }

    public int reason() {
        return this.reason;
    }

    public ClientboundKickoutVoteStatusPacket target(final int target) {
        this.target = target;
        return this;
    }

    public int target() {
        return this.target;
    }

    public ClientboundKickoutVoteStatusPacket targetNickname(final String targetNickname) {
        this.targetNickname = targetNickname;
        return this;
    }

    public String targetNickname() {
        return this.targetNickname;
    }

    public ClientboundKickoutVoteStatusPacket isVoteAble(final boolean isVoteAble) {
        this.isVoteAble = isVoteAble;
        return this;
    }

    public boolean isVoteAble() {
        return this.isVoteAble;
    }

    public ClientboundKickoutVoteStatusPacket isVoted(final boolean isVoted) {
        this.isVoted = isVoted;
        return this;
    }

    public boolean isVoted() {
        return this.isVoted;
    }

    public ClientboundKickoutVoteStatusPacket remainTime(final int remainTime) {
        this.remainTime = remainTime;
        return this;
    }

    public int remainTime() {
        return this.remainTime;
    }

    @Override
    public int packetId() {
        return 498;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.yes);
        buf.writeInt(this.no);
        buf.writeInt(this.total);
        buf.writeInt(this.reason);
        buf.writeInt(this.target);
        buf.writeString(this.targetNickname);
        buf.writeBoolean(this.isVoteAble);
        buf.writeBoolean(this.isVoted);
        buf.writeInt(this.remainTime);
    }
}