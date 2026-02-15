package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRcvPresentPacket implements IClientboundPacket {

	private long val;
	private long val2;
	private String val3;
	private int val4;
	private int val5;

	public final ClientboundRcvPresentPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundRcvPresentPacket val2(long val2) {
		this.val2 = val2;
		return this;
	}

	public final long val2() {
		return this.val2;
	}

	public final ClientboundRcvPresentPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundRcvPresentPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundRcvPresentPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 133;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
		buf.writeLong(this.val2);
		buf.writeString(this.val3);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
	}
}