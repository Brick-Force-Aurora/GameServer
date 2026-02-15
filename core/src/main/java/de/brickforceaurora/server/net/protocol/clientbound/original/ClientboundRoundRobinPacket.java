package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRoundRobinPacket implements IClientboundPacket {

	private String val;
	private int val2;

	public final ClientboundRoundRobinPacket val(String val) {
		this.val = val;
		return this;
	}

	public final String val() {
		return this.val;
	}

	public final ClientboundRoundRobinPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 139;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeString(this.val);
		buf.writeInt(this.val2);
	}
}