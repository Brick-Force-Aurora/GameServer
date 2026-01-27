package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundRoomListPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private String val4;
	private boolean val5;
	private int val6;
	private int val7;
	private int val8;
	private int val9;
	private String val10;
	private int val11;
	private int val12;
	private int val13;
	private int val14;
	private int val15;
	private int val16;
	private int val17;
	private boolean val18;
	private boolean val19;
	private boolean val20;
	private int val21;
	private int val22;

	public final ClientboundRoomListPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundRoomListPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundRoomListPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundRoomListPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundRoomListPacket val5(boolean val5) {
		this.val5 = val5;
		return this;
	}

	public final boolean val5() {
		return this.val5;
	}

	public final ClientboundRoomListPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundRoomListPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundRoomListPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundRoomListPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundRoomListPacket val10(String val10) {
		this.val10 = val10;
		return this;
	}

	public final String val10() {
		return this.val10;
	}

	public final ClientboundRoomListPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundRoomListPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundRoomListPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundRoomListPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundRoomListPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundRoomListPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundRoomListPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundRoomListPacket val18(boolean val18) {
		this.val18 = val18;
		return this;
	}

	public final boolean val18() {
		return this.val18;
	}

	public final ClientboundRoomListPacket val19(boolean val19) {
		this.val19 = val19;
		return this;
	}

	public final boolean val19() {
		return this.val19;
	}

	public final ClientboundRoomListPacket val20(boolean val20) {
		this.val20 = val20;
		return this;
	}

	public final boolean val20() {
		return this.val20;
	}

	public final ClientboundRoomListPacket val21(int val21) {
		this.val21 = val21;
		return this;
	}

	public final int val21() {
		return this.val21;
	}

	public final ClientboundRoomListPacket val22(int val22) {
		this.val22 = val22;
		return this;
	}

	public final int val22() {
		return this.val22;
	}

	@Override
	public int packetId() {
		return 468;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.val2);
		buffer.writeIntLE(this.val3);
		if (this.val4.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeBoolean(this.val5);
		buffer.writeIntLE(this.val6);
		buffer.writeIntLE(this.val7);
		buffer.writeIntLE(this.val8);
		buffer.writeIntLE(this.val9);
		if (this.val10.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val10.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val11);
		buffer.writeIntLE(this.val12);
		buffer.writeIntLE(this.val13);
		buffer.writeIntLE(this.val14);
		buffer.writeIntLE(this.val15);
		buffer.writeIntLE(this.val16);
		buffer.writeIntLE(this.val17);
		buffer.writeBoolean(this.val18);
		buffer.writeBoolean(this.val19);
		buffer.writeBoolean(this.val20);
		buffer.writeIntLE(this.val21);
		buffer.writeIntLE(this.val22);
	}
}