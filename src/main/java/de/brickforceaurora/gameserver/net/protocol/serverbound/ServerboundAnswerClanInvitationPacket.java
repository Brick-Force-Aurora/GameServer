package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		this.memoSeq = buffer.readLongLE();
		this.clan = buffer.readIntLE();
		this.accept = buffer.readBoolean();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.title = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.title = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.contents = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.contents = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}