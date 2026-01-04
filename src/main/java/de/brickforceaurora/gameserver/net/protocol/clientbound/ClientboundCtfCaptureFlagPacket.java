package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCtfCaptureFlagPacket implements IClientboundPacket {

	private int val;
	private int Unnamed0;
	private boolean Unnamed1;

	public final ClientboundCtfCaptureFlagPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundCtfCaptureFlagPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundCtfCaptureFlagPacket Unnamed1(boolean Unnamed1) {
		this.Unnamed1 = Unnamed1;
		return this;
	}

	public final boolean Unnamed1() {
		return this.Unnamed1;
	}

	@Override
	public int packetId() {
		return 288;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.Unnamed0);
		buffer.writeBoolean(this.Unnamed1);
	}
}