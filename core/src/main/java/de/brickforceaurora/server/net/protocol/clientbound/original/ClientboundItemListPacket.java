package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundItemListPacket implements IClientboundPacket {

	private int val;
	private long val2;
	private String val3;
	private byte val4;
	private int val5;
	private byte val6;
	private int val7;

	public final ClientboundItemListPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundItemListPacket val2(long val2) {
		this.val2 = val2;
		return this;
	}

	public final long val2() {
		return this.val2;
	}

	public final ClientboundItemListPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundItemListPacket val4(byte val4) {
		this.val4 = val4;
		return this;
	}

	public final byte val4() {
		return this.val4;
	}

	public final ClientboundItemListPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundItemListPacket val6(byte val6) {
		this.val6 = val6;
		return this;
	}

	public final byte val6() {
		return this.val6;
	}

	public final ClientboundItemListPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	@Override
	public int packetId() {
		return 464;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeLong(this.val2);
		buf.writeString(this.val3);
		buf.writeByte(this.val4);
		buf.writeInt(this.val5);
		buf.writeByte(this.val6);
		buf.writeInt(this.val7);
	}
}