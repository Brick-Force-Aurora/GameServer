package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundShooterToolPacket implements IClientboundPacket {

	private byte val;
	private long val2;

	public final ClientboundShooterToolPacket val(byte val) {
		this.val = val;
		return this;
	}

	public final byte val() {
		return this.val;
	}

	public final ClientboundShooterToolPacket val2(long val2) {
		this.val2 = val2;
		return this;
	}

	public final long val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 332;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.val);
		buf.writeLong(this.val2);
	}
}