package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanCancelApplicationPacket implements IServerboundPacket {

	private String clanName;

	public final ServerboundClanCancelApplicationPacket clanName(String clanName) {
		this.clanName = clanName;
		return this;
	}

	public final String clanName() {
		return this.clanName;
	}

	@Override
	public int packetId() {
		return 557;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clanName = buf.readString();
	}
}