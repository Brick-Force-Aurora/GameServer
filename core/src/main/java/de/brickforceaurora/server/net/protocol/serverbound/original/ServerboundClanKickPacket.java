package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanKickPacket implements IServerboundPacket {

	private int clan;
	private int kick;
	private String nickname;
	private String title;
	private String contents;

	public final ServerboundClanKickPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundClanKickPacket kick(int kick) {
		this.kick = kick;
		return this;
	}

	public final int kick() {
		return this.kick;
	}

	public final ServerboundClanKickPacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	public final ServerboundClanKickPacket title(String title) {
		this.title = title;
		return this;
	}

	public final String title() {
		return this.title;
	}

	public final ServerboundClanKickPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 209;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.kick = buf.readInt();
		this.nickname = buf.readString();
		this.title = buf.readString();
		this.contents = buf.readString();
	}
}