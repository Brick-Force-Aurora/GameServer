package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChangeClanIntroPacket implements IServerboundPacket {

	private int clan;
	private String intro;

	public final ServerboundChangeClanIntroPacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundChangeClanIntroPacket intro(String intro) {
		this.intro = intro;
		return this;
	}

	public final String intro() {
		return this.intro;
	}

	@Override
	public int packetId() {
		return 223;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.intro = buf.readString();
	}
}