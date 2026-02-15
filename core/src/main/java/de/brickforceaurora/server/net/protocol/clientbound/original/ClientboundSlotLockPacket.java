package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundSlotLockPacket implements IClientboundPacket {

	private byte val;
	private byte val2;

	public final ClientboundSlotLockPacket val(byte val) {
		this.val = val;
		return this;
	}

	public final byte val() {
		return this.val;
	}

	public final ClientboundSlotLockPacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 86;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.val);
		buf.writeByte(this.val2);
	}
}