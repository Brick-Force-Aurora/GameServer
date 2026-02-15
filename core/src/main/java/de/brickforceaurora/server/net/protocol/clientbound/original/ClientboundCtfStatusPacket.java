package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundCtfStatusPacket implements IClientboundPacket {

	private int val;
	private int Unnamed0;
	private float val3;
	private float val4;
	private float val5;

	public final ClientboundCtfStatusPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundCtfStatusPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundCtfStatusPacket val3(float val3) {
		this.val3 = val3;
		return this;
	}

	public final float val3() {
		return this.val3;
	}

	public final ClientboundCtfStatusPacket val4(float val4) {
		this.val4 = val4;
		return this;
	}

	public final float val4() {
		return this.val4;
	}

	public final ClientboundCtfStatusPacket val5(float val5) {
		this.val5 = val5;
		return this;
	}

	public final float val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 302;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.Unnamed0);
		buf.writeFloat(this.val3);
		buf.writeFloat(this.val4);
		buf.writeFloat(this.val5);
	}
}