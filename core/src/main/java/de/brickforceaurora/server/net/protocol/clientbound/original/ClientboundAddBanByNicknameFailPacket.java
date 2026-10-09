package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAddBanByNicknameFailPacket implements IClientboundPacket {

	private String nickName;

	public final ClientboundAddBanByNicknameFailPacket nickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public final String nickName() {
		return this.nickName;
	}

	@Override
	public int packetId() {
		return 118;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(0); //unused
		buf.writeString(this.nickName);
	}
}