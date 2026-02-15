package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundGadgetPacket implements IClientboundPacket {

	private int val;
	private int Unnamed0;
	private int Unnamed1;
	private int val4;
	private float val5;
	private float val6;
	private float val7;
	private float val8;
	private float val9;
	private float val10;

	public final ClientboundGadgetPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundGadgetPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundGadgetPacket Unnamed1(int Unnamed1) {
		this.Unnamed1 = Unnamed1;
		return this;
	}

	public final int Unnamed1() {
		return this.Unnamed1;
	}

	public final ClientboundGadgetPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundGadgetPacket val5(float val5) {
		this.val5 = val5;
		return this;
	}

	public final float val5() {
		return this.val5;
	}

	public final ClientboundGadgetPacket val6(float val6) {
		this.val6 = val6;
		return this;
	}

	public final float val6() {
		return this.val6;
	}

	public final ClientboundGadgetPacket val7(float val7) {
		this.val7 = val7;
		return this;
	}

	public final float val7() {
		return this.val7;
	}

	public final ClientboundGadgetPacket val8(float val8) {
		this.val8 = val8;
		return this;
	}

	public final float val8() {
		return this.val8;
	}

	public final ClientboundGadgetPacket val9(float val9) {
		this.val9 = val9;
		return this;
	}

	public final float val9() {
		return this.val9;
	}

	public final ClientboundGadgetPacket val10(float val10) {
		this.val10 = val10;
		return this;
	}

	public final float val10() {
		return this.val10;
	}

	@Override
	public int packetId() {
		return 401;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.Unnamed0);
		buf.writeInt(this.Unnamed1);
		buf.writeInt(this.val4);
		buf.writeFloat(this.val5);
		buf.writeFloat(this.val6);
		buf.writeFloat(this.val7);
		buf.writeFloat(this.val8);
		buf.writeFloat(this.val9);
		buf.writeFloat(this.val10);
	}
}