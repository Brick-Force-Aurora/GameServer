package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAddBanByNicknamePacket implements IServerboundPacket {

	private String nickname;

	public final ServerboundAddBanByNicknamePacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	@Override
	public int packetId() {
		return 112;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.nickname = buf.readString();
	}
}