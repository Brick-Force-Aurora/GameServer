package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundCaptureTheFlagEndPacket implements IClientboundPacket {

	private byte val;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;
	private boolean val9;
	private int val10;
	private String val11;
	private int val12;
	private int val13;
	private int val14;
	private int val15;
	private int val16;
	private int val17;
	private int val18;
	private int val19;
	private int val20;
	private long val21;

	public final ClientboundCaptureTheFlagEndPacket val(byte val) {
		this.val = val;
		return this;
	}

	public final byte val() {
		return this.val;
	}

	public final ClientboundCaptureTheFlagEndPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundCaptureTheFlagEndPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundCaptureTheFlagEndPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundCaptureTheFlagEndPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundCaptureTheFlagEndPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundCaptureTheFlagEndPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundCaptureTheFlagEndPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundCaptureTheFlagEndPacket val9(boolean val9) {
		this.val9 = val9;
		return this;
	}

	public final boolean val9() {
		return this.val9;
	}

	public final ClientboundCaptureTheFlagEndPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundCaptureTheFlagEndPacket val11(String val11) {
		this.val11 = val11;
		return this;
	}

	public final String val11() {
		return this.val11;
	}

	public final ClientboundCaptureTheFlagEndPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundCaptureTheFlagEndPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundCaptureTheFlagEndPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundCaptureTheFlagEndPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundCaptureTheFlagEndPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundCaptureTheFlagEndPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundCaptureTheFlagEndPacket val18(int val18) {
		this.val18 = val18;
		return this;
	}

	public final int val18() {
		return this.val18;
	}

	public final ClientboundCaptureTheFlagEndPacket val19(int val19) {
		this.val19 = val19;
		return this;
	}

	public final int val19() {
		return this.val19;
	}

	public final ClientboundCaptureTheFlagEndPacket val20(int val20) {
		this.val20 = val20;
		return this;
	}

	public final int val20() {
		return this.val20;
	}

	public final ClientboundCaptureTheFlagEndPacket val21(long val21) {
		this.val21 = val21;
		return this;
	}

	public final long val21() {
		return this.val21;
	}

	@Override
	public int packetId() {
		return 292;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeByte(this.val);
		buffer.writeIntLE(this.val2);
		buffer.writeIntLE(this.val3);
		buffer.writeIntLE(this.val4);
		buffer.writeIntLE(this.val5);
		buffer.writeIntLE(this.val6);
		buffer.writeIntLE(this.val7);
		buffer.writeIntLE(this.val8);
		buffer.writeBoolean(this.val9);
		buffer.writeIntLE(this.val10);
		if (this.val11.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val11.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val12);
		buffer.writeIntLE(this.val13);
		buffer.writeIntLE(this.val14);
		buffer.writeIntLE(this.val15);
		buffer.writeIntLE(this.val16);
		buffer.writeIntLE(this.val17);
		buffer.writeIntLE(this.val18);
		buffer.writeIntLE(this.val19);
		buffer.writeIntLE(this.val20);
		buffer.writeLongLE(this.val21);
	}
}