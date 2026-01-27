package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundAcceptApplicantPacket implements IClientboundPacket {

	private int val;
	private int Unnamed0;
	private boolean val3;
	private String val4;

	public final ClientboundAcceptApplicantPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundAcceptApplicantPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundAcceptApplicantPacket val3(boolean val3) {
		this.val3 = val3;
		return this;
	}

	public final boolean val3() {
		return this.val3;
	}

	public final ClientboundAcceptApplicantPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	@Override
	public int packetId() {
		return 218;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.Unnamed0);
		buffer.writeBoolean(this.val3);
		if (this.val4.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
	}
}