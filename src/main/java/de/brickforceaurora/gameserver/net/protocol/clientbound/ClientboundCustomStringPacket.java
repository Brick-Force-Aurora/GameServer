package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundCustomStringPacket implements IClientboundPacket {

	private String val;
	private String val2;
	private String val3;
	private String val4;
	private String val5;
	private String val6;
	private String val7;
	private String val8;
	private String val9;
	private String val10;
	private String val11;
	private String val12;
	private String val13;

	public final ClientboundCustomStringPacket val(String val) {
		this.val = val;
		return this;
	}

	public final String val() {
		return this.val;
	}

	public final ClientboundCustomStringPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundCustomStringPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	public final ClientboundCustomStringPacket val4(String val4) {
		this.val4 = val4;
		return this;
	}

	public final String val4() {
		return this.val4;
	}

	public final ClientboundCustomStringPacket val5(String val5) {
		this.val5 = val5;
		return this;
	}

	public final String val5() {
		return this.val5;
	}

	public final ClientboundCustomStringPacket val6(String val6) {
		this.val6 = val6;
		return this;
	}

	public final String val6() {
		return this.val6;
	}

	public final ClientboundCustomStringPacket val7(String val7) {
		this.val7 = val7;
		return this;
	}

	public final String val7() {
		return this.val7;
	}

	public final ClientboundCustomStringPacket val8(String val8) {
		this.val8 = val8;
		return this;
	}

	public final String val8() {
		return this.val8;
	}

	public final ClientboundCustomStringPacket val9(String val9) {
		this.val9 = val9;
		return this;
	}

	public final String val9() {
		return this.val9;
	}

	public final ClientboundCustomStringPacket val10(String val10) {
		this.val10 = val10;
		return this;
	}

	public final String val10() {
		return this.val10;
	}

	public final ClientboundCustomStringPacket val11(String val11) {
		this.val11 = val11;
		return this;
	}

	public final String val11() {
		return this.val11;
	}

	public final ClientboundCustomStringPacket val12(String val12) {
		this.val12 = val12;
		return this;
	}

	public final String val12() {
		return this.val12;
	}

	public final ClientboundCustomStringPacket val13(String val13) {
		this.val13 = val13;
		return this;
	}

	public final String val13() {
		return this.val13;
	}

	@Override
	public int packetId() {
		return 422;
	}

	@Override
	public final void write(ByteBuf buffer) {
		if (this.val.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val2.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val3.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val4.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val5.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val5.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val6.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val6.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val7.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val7.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val8.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val8.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val9.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val9.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val10.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val10.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val11.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val11.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val12.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val12.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		if (this.val13.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val13.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
	}
}