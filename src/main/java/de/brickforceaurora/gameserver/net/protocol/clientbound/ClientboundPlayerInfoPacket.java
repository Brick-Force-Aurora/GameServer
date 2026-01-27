package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundPlayerInfoPacket implements IClientboundPacket {

	private String val;
	private int val2;
	private int val3;
	private int val4;
	private int val5;
	private int Unnamed0;
	private int val7;
	private int val8;
	private int val9;
	private int val10;
	private int val11;
	private int val12;
	private String val13;
	private int val14;
	private int val15;
	private int val16;
	private int val17;
	private int val18;
	private int val19;
	private int val20;
	private int val21;
	private int val22;
	private int val23;

	public final ClientboundPlayerInfoPacket val(String val) {
		this.val = val;
		return this;
	}

	public final String val() {
		return this.val;
	}

	public final ClientboundPlayerInfoPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundPlayerInfoPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	public final ClientboundPlayerInfoPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundPlayerInfoPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundPlayerInfoPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundPlayerInfoPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundPlayerInfoPacket val8(int val8) {
		this.val8 = val8;
		return this;
	}

	public final int val8() {
		return this.val8;
	}

	public final ClientboundPlayerInfoPacket val9(int val9) {
		this.val9 = val9;
		return this;
	}

	public final int val9() {
		return this.val9;
	}

	public final ClientboundPlayerInfoPacket val10(int val10) {
		this.val10 = val10;
		return this;
	}

	public final int val10() {
		return this.val10;
	}

	public final ClientboundPlayerInfoPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundPlayerInfoPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundPlayerInfoPacket val13(String val13) {
		this.val13 = val13;
		return this;
	}

	public final String val13() {
		return this.val13;
	}

	public final ClientboundPlayerInfoPacket val14(int val14) {
		this.val14 = val14;
		return this;
	}

	public final int val14() {
		return this.val14;
	}

	public final ClientboundPlayerInfoPacket val15(int val15) {
		this.val15 = val15;
		return this;
	}

	public final int val15() {
		return this.val15;
	}

	public final ClientboundPlayerInfoPacket val16(int val16) {
		this.val16 = val16;
		return this;
	}

	public final int val16() {
		return this.val16;
	}

	public final ClientboundPlayerInfoPacket val17(int val17) {
		this.val17 = val17;
		return this;
	}

	public final int val17() {
		return this.val17;
	}

	public final ClientboundPlayerInfoPacket val18(int val18) {
		this.val18 = val18;
		return this;
	}

	public final int val18() {
		return this.val18;
	}

	public final ClientboundPlayerInfoPacket val19(int val19) {
		this.val19 = val19;
		return this;
	}

	public final int val19() {
		return this.val19;
	}

	public final ClientboundPlayerInfoPacket val20(int val20) {
		this.val20 = val20;
		return this;
	}

	public final int val20() {
		return this.val20;
	}

	public final ClientboundPlayerInfoPacket val21(int val21) {
		this.val21 = val21;
		return this;
	}

	public final int val21() {
		return this.val21;
	}

	public final ClientboundPlayerInfoPacket val22(int val22) {
		this.val22 = val22;
		return this;
	}

	public final int val22() {
		return this.val22;
	}

	public final ClientboundPlayerInfoPacket val23(int val23) {
		this.val23 = val23;
		return this;
	}

	public final int val23() {
		return this.val23;
	}

	@Override
	public int packetId() {
		return 27;
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
		buffer.writeIntLE(this.val2);
		buffer.writeIntLE(this.val3);
		buffer.writeIntLE(this.val4);
		buffer.writeIntLE(this.val5);
		buffer.writeIntLE(this.Unnamed0);
		buffer.writeIntLE(this.val7);
		buffer.writeIntLE(this.val8);
		buffer.writeIntLE(this.val9);
		buffer.writeIntLE(this.val10);
		buffer.writeIntLE(this.val11);
		buffer.writeIntLE(this.val12);
		if (this.val13.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.val13.getBytes(StandardCharsets.UTF_16LE);
			buffer.writeIntLE(bytes.length);
			buffer.writeBytes(bytes);
		}
		buffer.writeIntLE(this.val14);
		buffer.writeIntLE(this.val15);
		buffer.writeIntLE(this.val16);
		buffer.writeIntLE(this.val17);
		buffer.writeIntLE(this.val18);
		buffer.writeIntLE(this.val19);
		buffer.writeIntLE(this.val20);
		buffer.writeIntLE(this.val21);
		buffer.writeIntLE(this.val22);
		buffer.writeIntLE(this.val23);
	}
}