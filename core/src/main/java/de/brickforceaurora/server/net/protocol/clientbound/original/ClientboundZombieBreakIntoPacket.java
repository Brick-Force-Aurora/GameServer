package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundZombieBreakIntoPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	private int val6;
	private int val7;
	private int val8;

	public final ClientboundZombieBreakIntoPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundZombieBreakIntoPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundZombieBreakIntoPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundZombieBreakIntoPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundZombieBreakIntoPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundZombieBreakIntoPacket val6(int val6) {
		this.val6 = val6;
		return this;
	}

	public final int val6() {
		return this.val6;
	}

	public final ClientboundZombieBreakIntoPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundZombieBreakIntoPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	@Override
	public int packetId() {
		return 546;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeInt(this.val3);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
		buf.writeInt(this.val6);
		buf.writeInt(this.val7);
		buf.writeInt(this.val8);
	}
}