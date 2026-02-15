package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAnswerClanInvitationPacket implements IServerboundPacket {

	private long memoSeq;
	private int clan;
	private boolean accept;
	private String title;
	private String contents;

	public final ServerboundAnswerClanInvitationPacket memoSeq(long memoSeq) {
		this.memoSeq = memoSeq;
		return this;
	}

	public final long memoSeq() {
		return this.memoSeq;
	}

	public final ServerboundAnswerClanInvitationPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundAnswerClanInvitationPacket accept(boolean accept) {
		this.accept = accept;
		return this;
	}

	public final boolean accept() {
		return this.accept;
	}

	public final ServerboundAnswerClanInvitationPacket title(String title) {
		this.title = title;
		return this;
	}

	public final String title() {
		return this.title;
	}

	public final ServerboundAnswerClanInvitationPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 195;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.memoSeq = buf.readLong();
		this.clan = buf.readInt();
		this.accept = buf.readBoolean();
		this.title = buf.readString();
		this.contents = buf.readString();
	}
}