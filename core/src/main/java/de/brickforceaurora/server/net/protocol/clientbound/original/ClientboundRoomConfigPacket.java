package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRoomConfigPacket implements IClientboundPacket {

	private int val;
	private String val2;
	private int val3;
	private int val4;
	private int val5;
	private boolean val6;
	private boolean val7;
	private boolean val8;
	private String val9;
	private int val10;
	private int val11;
	private boolean val12;
	private boolean val13;

	public final ClientboundRoomConfigPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundRoomConfigPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundRoomConfigPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundRoomConfigPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundRoomConfigPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundRoomConfigPacket val6(boolean val6) {
		this.val6 = val6;
		return this;
	}

	public final boolean val6() {
		return this.val6;
	}

	public final ClientboundRoomConfigPacket val7(boolean val7) {
		this.val7 = val7;
		return this;
	}

	public final boolean val7() {
		return this.val7;
	}

	public final ClientboundRoomConfigPacket val8(boolean val8) {
		this.val8 = val8;
		return this;
	}

	public final boolean val8() {
		return this.val8;
	}

	public final ClientboundRoomConfigPacket val9(String val9) {
		this.val9 = val9;
		return this;
	}

	public final String val9() {
		return this.val9;
	}

	public final ClientboundRoomConfigPacket val10(int val10) {
		if (val10 > 255L || val10 < 0L) {
			throw new IllegalArgumentException(
					"Value " + val10 + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundRoomConfigPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundRoomConfigPacket val12(boolean val12) {
		this.val12 = val12;
		return this;
	}

	public final boolean val12() {
		return this.val12;
	}

	public final ClientboundRoomConfigPacket val13(boolean val13) {
		this.val13 = val13;
		return this;
	}

	public final boolean val13() {
		return this.val13;
	}

	@Override
	public int packetId() {
		return 92;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeString(this.val2);
		buf.writeInt(this.val3);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
		buf.writeBoolean(this.val6);
		buf.writeBoolean(this.val7);
		buf.writeBoolean(this.val8);
		buf.writeString(this.val9);
		buf.writeByte(this.val10);
		buf.writeInt(this.val11);
		buf.writeBoolean(this.val12);
		buf.writeBoolean(this.val13);
	}
}