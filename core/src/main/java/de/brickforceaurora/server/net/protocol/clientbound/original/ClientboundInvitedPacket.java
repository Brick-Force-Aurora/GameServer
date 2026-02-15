package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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

	public final ClientboundInvitedPacket invitorSeq(int invitorSeq) {
		this.invitorSeq = invitorSeq;
		return this;
	}

	public final int invitorSeq() {
		return this.invitorSeq;
	}

	public final ClientboundInvitedPacket invitorNickname(String invitorNickname) {
		this.invitorNickname = invitorNickname;
		return this;
	}

	public final String invitorNickname() {
		return this.invitorNickname;
	}

	public final ClientboundInvitedPacket channelIndex(int channelIndex) {
		this.channelIndex = channelIndex;
		return this;
	}

	public final int channelIndex() {
		return this.channelIndex;
	}

	public final ClientboundInvitedPacket roomNo(int roomNo) {
		this.roomNo = roomNo;
		return this;
	}

	public final int roomNo() {
		return this.roomNo;
	}

	public final ClientboundInvitedPacket mode(int mode) {
		this.mode = mode;
		return this;
	}

	public final int mode() {
		return this.mode;
	}

	public final ClientboundInvitedPacket pswd(String pswd) {
		this.pswd = pswd;
		return this;
	}

	public final String pswd() {
		return this.pswd;
	}

	public final ClientboundInvitedPacket clanSeq(int clanSeq) {
		this.clanSeq = clanSeq;
		return this;
	}

	public final int clanSeq() {
		return this.clanSeq;
	}

	public final ClientboundInvitedPacket squadIndex(int squadIndex) {
		this.squadIndex = squadIndex;
		return this;
	}

	public final int squadIndex() {
		return this.squadIndex;
	}

	public final ClientboundInvitedPacket squadCounterIndex(int squadCounterIndex) {
		this.squadCounterIndex = squadCounterIndex;
		return this;
	}

	public final int squadCounterIndex() {
		return this.squadCounterIndex;
	}

	@Override
	public int packetId() {
		return 483;
	}

	@Override
	public final void write(PacketBuf buf) {
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