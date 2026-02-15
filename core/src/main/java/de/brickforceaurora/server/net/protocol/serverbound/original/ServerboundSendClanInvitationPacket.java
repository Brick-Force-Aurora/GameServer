package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSendClanInvitationPacket implements IServerboundPacket {

	private int inviteeSeq;
	private String invitee;
	private String title;
	private String contents;

	public final ServerboundSendClanInvitationPacket inviteeSeq(int inviteeSeq) {
		this.inviteeSeq = inviteeSeq;
		return this;
	}

	public final int inviteeSeq() {
		return this.inviteeSeq;
	}

	public final ServerboundSendClanInvitationPacket invitee(String invitee) {
		this.invitee = invitee;
		return this;
	}

	public final String invitee() {
		return this.invitee;
	}

	public final ServerboundSendClanInvitationPacket title(String title) {
		this.title = title;
		return this;
	}

	public final String title() {
		return this.title;
	}

	public final ServerboundSendClanInvitationPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 193;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.inviteeSeq = buf.readInt();
		this.invitee = buf.readString();
		this.title = buf.readString();
		this.contents = buf.readString();
	}
}