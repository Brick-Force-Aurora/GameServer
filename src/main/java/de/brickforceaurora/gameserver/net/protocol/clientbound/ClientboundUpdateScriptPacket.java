package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundUpdateScriptPacket implements IClientboundPacket {

	private int val;
	private String val2;
	private boolean val3;
	private boolean val4;
	private String val5;

	public final ClientboundUpdateScriptPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundUpdateScriptPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundUpdateScriptPacket val3(boolean val3) {
		this.val3 = val3;
		return this;
	}

	public final boolean val3() {
		return this.val3;
	}

	public final ClientboundUpdateScriptPacket val4(boolean val4) {
		this.val4 = val4;
		return this;
	}

	public final boolean val4() {
		return this.val4;
	}

	public final ClientboundUpdateScriptPacket val5(String val5) {
		this.val5 = val5;
		return this;
	}

	public final String val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 168;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		if (this.val2.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeBoolean(this.val3);
		buffer.writeBoolean(this.val4);
		if (this.val5.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val5.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
	}
}