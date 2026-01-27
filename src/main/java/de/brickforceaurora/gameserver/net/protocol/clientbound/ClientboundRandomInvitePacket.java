package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundRandomInvitePacket implements IClientboundPacket {

	private int val;
	private int Unnamed0;
	private String val3;

	public final ClientboundRandomInvitePacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundRandomInvitePacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundRandomInvitePacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 518;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.Unnamed0);
		if (this.val3.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
	}
}