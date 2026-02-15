package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAutoLoginToRunupPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private boolean val3;

	public final ClientboundAutoLoginToRunupPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundAutoLoginToRunupPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundAutoLoginToRunupPacket val3(boolean val3) {
		this.val3 = val3;
		return this;
	}

	public final boolean val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 410;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeBoolean(this.val3);
	}
}