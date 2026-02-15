package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundClanChangeRoomNamePacket implements IClientboundPacket {

	private String val;

	public final ClientboundClanChangeRoomNamePacket val(String val) {
		this.val = val;
		return this;
	}

	public final String val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 564;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeString(this.val);
	}
}