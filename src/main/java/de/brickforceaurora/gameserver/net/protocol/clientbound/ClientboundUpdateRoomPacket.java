package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundUpdateRoomPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private int val4;
	private boolean val5;
	private int val6;
	private String val7;
	private int val8;
	private int val9;
	private int val10;
	private int val11;
	private int val12;
	private int val13;
	private int val14;
	private boolean val15;
	private int val16;
	private String val17;
	private boolean val18;
	private boolean val19;
	private int val20;
	private int val21;

	public final ClientboundUpdateRoomPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundUpdateRoomPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundUpdateRoomPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundUpdateRoomPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundUpdateRoomPacket val5(boolean val5) {
		this.val5 = val5;
		return this;
	}

	public final boolean val5() {
		return this.val5;
	}

	public final ClientboundUpdateRoomPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundUpdateRoomPacket val7(String val7) {
		this.val7 = val7;
		return this;
	}

	public final String val7() {
		return this.val7;
	}

	public final ClientboundUpdateRoomPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundUpdateRoomPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundUpdateRoomPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundUpdateRoomPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundUpdateRoomPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundUpdateRoomPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundUpdateRoomPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundUpdateRoomPacket val15(boolean val15) {
		this.val15 = val15;
		return this;
	}

	public final boolean val15() {
		return this.val15;
	}

	public final ClientboundUpdateRoomPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundUpdateRoomPacket val17(String val17) {
		this.val17 = val17;
		return this;
	}

	public final String val17() {
		return this.val17;
	}

	public final ClientboundUpdateRoomPacket val18(boolean val18) {
		this.val18 = val18;
		return this;
	}

	public final boolean val18() {
		return this.val18;
	}

	public final ClientboundUpdateRoomPacket val19(boolean val19) {
		this.val19 = val19;
		return this;
	}

	public final boolean val19() {
		return this.val19;
	}

	public final ClientboundUpdateRoomPacket val20(int val20) {
		this.val20 = val20;
		return this;
	}

	public final int val20() {
		return this.val20;
	}

	public final ClientboundUpdateRoomPacket val21(int val21) {
		this.val21 = val21;
		return this;
	}

	public final int val21() {
		return this.val21;
	}

	@Override
	public int packetId() {
		return 30;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.val2);
		buffer.writeIntLE(this.val3);
		buffer.writeIntLE(this.val4);
		buffer.writeBoolean(this.val5);
		buffer.writeIntLE(this.val6);
		if (this.val7.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val7.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val8);
		buffer.writeIntLE(this.val9);
		buffer.writeIntLE(this.val10);
		buffer.writeIntLE(this.val11);
		buffer.writeIntLE(this.val12);
		buffer.writeIntLE(this.val13);
		buffer.writeIntLE(this.val14);
		buffer.writeBoolean(this.val15);
		buffer.writeIntLE(this.val16);
		if (this.val17.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val17.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeBoolean(this.val18);
		buffer.writeBoolean(this.val19);
		buffer.writeIntLE(this.val20);
		buffer.writeIntLE(this.val21);
	}
}