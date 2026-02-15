package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundWeaponSlotPacket implements IClientboundPacket {

	private int val;
	private long val2;

	public final ClientboundWeaponSlotPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundWeaponSlotPacket val2(long val2) {
		this.val2 = val2;
		return this;
	}

	public final long val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 418;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeLong(this.val2);
	}
}