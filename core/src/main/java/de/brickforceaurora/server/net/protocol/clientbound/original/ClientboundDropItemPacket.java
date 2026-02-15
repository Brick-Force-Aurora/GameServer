package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDropItemPacket implements IClientboundPacket {

	private int val;
	private String val2;
	private int val3;
	private int val4;
	private float val5;
	private float val6;
	private float val7;

	public final ClientboundDropItemPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundDropItemPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundDropItemPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundDropItemPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundDropItemPacket val5(float val5) {
		this.val5 = val5;
		return this;
	}

	public final float val5() {
		return this.val5;
	}

	public final ClientboundDropItemPacket val6(float val6) {
		this.val6 = val6;
		return this;
	}

	public final float val6() {
		return this.val6;
	}

	public final ClientboundDropItemPacket val7(float val7) {
		this.val7 = val7;
		return this;
	}

	public final float val7() {
		return this.val7;
	}

	@Override
	public int packetId() {
		return 527;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeString(this.val2);
		buf.writeInt(this.val3);
		buf.writeInt(this.val4);
		buf.writeFloat(this.val5);
		buf.writeFloat(this.val6);
		buf.writeFloat(this.val7);
	}
}