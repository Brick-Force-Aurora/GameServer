package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChangeClanNoticePacket implements IServerboundPacket {

	private int clan;
	private String notice;

	public final ServerboundChangeClanNoticePacket clan(int clan) {
		this.clan = clan;
		return this;
	}

	public final int clan() {
		return this.clan;
	}

	public final ServerboundChangeClanNoticePacket notice(String notice) {
		this.notice = notice;
		return this;
	}

	public final String notice() {
		return this.notice;
	}

	@Override
	public int packetId() {
		return 225;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clan = buf.readInt();
		this.notice = buf.readString();
	}
}