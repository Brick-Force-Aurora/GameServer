package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundWeaponChangePacket implements IClientboundPacket {

	private int val;
	private int Unnamed0;
	private long val3;

	public final ClientboundWeaponChangePacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundWeaponChangePacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundWeaponChangePacket val3(long val3) {
		this.val3 = val3;
		return this;
	}

	public final long val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 415;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.Unnamed0);
		buf.writeLong(this.val3);
	}
}