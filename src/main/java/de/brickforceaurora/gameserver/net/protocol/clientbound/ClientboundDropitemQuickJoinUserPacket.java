package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundDropitemQuickJoinUserPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private String val3;
	private int val4;
	private int val5;
	private float val6;
	private float val7;
	private float val8;

	public final ClientboundDropitemQuickJoinUserPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundDropitemQuickJoinUserPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundDropitemQuickJoinUserPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundDropitemQuickJoinUserPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundDropitemQuickJoinUserPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundDropitemQuickJoinUserPacket val6(float val6) {
		this.val6 = val6;
		return this;
	}

	public final float val6() {
		return this.val6;
	}

	public final ClientboundDropitemQuickJoinUserPacket val7(float val7) {
		this.val7 = val7;
		return this;
	}

	public final float val7() {
		return this.val7;
	}

	public final ClientboundDropitemQuickJoinUserPacket val8(float val8) {
		this.val8 = val8;
		return this;
	}

	public final float val8() {
		return this.val8;
	}

	@Override
	public int packetId() {
		return 531;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.val);
		buffer.writeIntLE(this.val2);
		if (this.val3.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val4);
		buffer.writeIntLE(this.val5);
		buffer.writeFloatLE(this.val6);
		buffer.writeFloatLE(this.val7);
		buffer.writeFloatLE(this.val8);
	}
}