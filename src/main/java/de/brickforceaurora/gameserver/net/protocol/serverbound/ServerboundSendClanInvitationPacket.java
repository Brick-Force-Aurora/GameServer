package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		this.inviteeSeq = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.invitee = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.invitee = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
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