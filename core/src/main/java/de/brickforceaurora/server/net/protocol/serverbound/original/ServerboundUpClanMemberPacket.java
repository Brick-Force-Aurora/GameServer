package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUpClanMemberPacket implements IServerboundPacket {

	private int clan;
	private int member;
	private String nickname;
	private String title;
	private String contents;

	public final ServerboundUpClanMemberPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundUpClanMemberPacket member(int member) {
		this.member = member;
		return this;
	}

	public final int member() {
		return this.member;
	}

	public final ServerboundUpClanMemberPacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	public final ServerboundUpClanMemberPacket title(String title) {
		this.title = title;
		return this;
	}

	public final String title() {
		return this.title;
	}

	public final ServerboundUpClanMemberPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 211;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.member = buf.readInt();
		this.nickname = buf.readString();
		this.title = buf.readString();
		this.contents = buf.readString();
	}
}