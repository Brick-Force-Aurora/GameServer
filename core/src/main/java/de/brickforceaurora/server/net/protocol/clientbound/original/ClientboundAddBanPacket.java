package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAddBanPacket implements IClientboundPacket {

	private int playerId;
	private String nickName;

	public final ClientboundAddBanPacket playerId(int playerId) {
		this.playerId = playerId;
		return this;
	}

	public final int playerId() {
		return this.playerId;
	}

	public final ClientboundAddBanPacket nickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public final String nickName() {
		return this.nickName;
	}

	@Override
	public int packetId() {
		return 106;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.playerId);
		buf.writeString(this.nickName);
	}
}