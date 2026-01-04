package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundUseChangeNicknamePacket implements IClientboundPacket {

	private int val;
	private String val2;
	private String Unnamed0;

	public final ClientboundUseChangeNicknamePacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundUseChangeNicknamePacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundUseChangeNicknamePacket Unnamed0(String Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final String Unnamed0() {
		return this.Unnamed0;
	}

	@Override
	public int packetId() {
		return 502;
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
		if (this.Unnamed0.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.Unnamed0.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
	}
}