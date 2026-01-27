package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundAcceptApplicantPacket implements IServerboundPacket {

	private int clan;
	private int member;
	private String nickname;
	private boolean accept;
	private String title;
	private String contents;

	public final ServerboundAcceptApplicantPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundAcceptApplicantPacket member(int member) {
		this.member = member;
		return this;
	}

	public final int member() {
		return this.member;
	}

	public final ServerboundAcceptApplicantPacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	public final ServerboundAcceptApplicantPacket accept(boolean accept) {
		this.accept = accept;
		return this;
	}

	public final boolean accept() {
		return this.accept;
	}

	public final ServerboundAcceptApplicantPacket title(String title) {
		this.title = title;
		return this;
	}

	public final String title() {
		return this.title;
	}

	public final ServerboundAcceptApplicantPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 217;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clan = buffer.readIntLE();
		this.member = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.nickname = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.nickname = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
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