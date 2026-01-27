package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundZombieEndPacket implements IClientboundPacket {

	private int val;
	private boolean val2;
	private int val3;
	private String val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;
	private int val9;
	private int val10;
	private int val11;
	private int val12;
	private int val13;
	private long val14;

	public final ClientboundZombieEndPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundZombieEndPacket val2(boolean val2) {
		this.val2 = val2;
		return this;
	}

	public final boolean val2() {
		return this.val2;
	}

	public final ClientboundZombieEndPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundZombieEndPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundZombieEndPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundZombieEndPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundZombieEndPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundZombieEndPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundZombieEndPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundZombieEndPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundZombieEndPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundZombieEndPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundZombieEndPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundZombieEndPacket val14(long val14) {
		this.val14 = val14;
		return this;
	}

	public final long val14() {
		return this.val14;
	}

	@Override
	public int packetId() {
		return 537;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeBoolean(this.val2);
		buffer.writeIntLE(this.val3);
		if (this.val4.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val5);
		buffer.writeIntLE(this.val6);
		buffer.writeIntLE(this.val7);
		buffer.writeIntLE(this.val8);
		buffer.writeIntLE(this.val9);
		buffer.writeIntLE(this.val10);
		buffer.writeIntLE(this.val11);
		buffer.writeIntLE(this.val12);
		buffer.writeIntLE(this.val13);
		buffer.writeLongLE(this.val14);
	}
}