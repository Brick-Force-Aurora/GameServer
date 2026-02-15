package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMutePacket implements IServerboundPacket {

	private String nickname;
	private int howlong;

	public final ServerboundMutePacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	public final ServerboundMutePacket howlong(int howlong) {
		this.howlong = howlong;
		return this;
	}

	public final int howlong() {
		return this.howlong;
	}

	@Override
	public int packetId() {
		return 455;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.nickname = buf.readString();
		this.howlong = buf.readInt();
	}
}