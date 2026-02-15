package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAutoLoginToNetmarblePacket implements IClientboundPacket {

	private int val;
	private int val2;
	private boolean val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;

	public final ClientboundAutoLoginToNetmarblePacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundAutoLoginToNetmarblePacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundAutoLoginToNetmarblePacket val3(boolean val3) {
		this.val3 = val3;
		return this;
	}

	public final boolean val3() {
		return this.val3;
	}

	public final ClientboundAutoLoginToNetmarblePacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundAutoLoginToNetmarblePacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundAutoLoginToNetmarblePacket val6(int val6) {
		if (val6 > 255L || val6 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val6 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundAutoLoginToNetmarblePacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	@Override
	public int packetId() {
		return 459;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeBoolean(this.val3);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
		buf.writeByte(this.val6);
		buf.writeInt(this.val7);
	}
}