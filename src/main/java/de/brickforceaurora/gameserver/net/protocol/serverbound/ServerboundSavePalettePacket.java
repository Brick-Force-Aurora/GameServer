package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundSavePalettePacket implements IServerboundPacket {

	private int pal0;
	private int pal1;
	private int pal2;
	private int pal3;
	private int pal4;
	private int pal5;
	private int pal6;
	private int pal7;
	private int pal8;
	private int pal9;

	public final ServerboundSavePalettePacket pal0(int pal0) {
		this.pal0 = pal0;
		return this;
	}

	public final int pal0() {
		return this.pal0;
	}

	public final ServerboundSavePalettePacket pal1(int pal1) {
		this.pal1 = pal1;
		return this;
	}

	public final int pal1() {
		return this.pal1;
	}

	public final ServerboundSavePalettePacket pal2(int pal2) {
		this.pal2 = pal2;
		return this;
	}

	public final int pal2() {
		return this.pal2;
	}

	public final ServerboundSavePalettePacket pal3(int pal3) {
		this.pal3 = pal3;
		return this;
	}

	public final int pal3() {
		return this.pal3;
	}

	public final ServerboundSavePalettePacket pal4(int pal4) {
		this.pal4 = pal4;
		return this;
	}

	public final int pal4() {
		return this.pal4;
	}

	public final ServerboundSavePalettePacket pal5(int pal5) {
		this.pal5 = pal5;
		return this;
	}

	public final int pal5() {
		return this.pal5;
	}

	public final ServerboundSavePalettePacket pal6(int pal6) {
		this.pal6 = pal6;
		return this;
	}

	public final int pal6() {
		return this.pal6;
	}

	public final ServerboundSavePalettePacket pal7(int pal7) {
		this.pal7 = pal7;
		return this;
	}

	public final int pal7() {
		return this.pal7;
	}

	public final ServerboundSavePalettePacket pal8(int pal8) {
		this.pal8 = pal8;
		return this;
	}

	public final int pal8() {
		return this.pal8;
	}

	public final ServerboundSavePalettePacket pal9(int pal9) {
		this.pal9 = pal9;
		return this;
	}

	public final int pal9() {
		return this.pal9;
	}

	@Override
	public int packetId() {
		return 19;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.pal0 = buffer.readIntLE();
		this.pal1 = buffer.readIntLE();
		this.pal2 = buffer.readIntLE();
		this.pal3 = buffer.readIntLE();
		this.pal4 = buffer.readIntLE();
		this.pal5 = buffer.readIntLE();
		this.pal6 = buffer.readIntLE();
		this.pal7 = buffer.readIntLE();
		this.pal8 = buffer.readIntLE();
		this.pal9 = buffer.readIntLE();
	}
}