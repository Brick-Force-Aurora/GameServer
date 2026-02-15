package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundWeaponModifierExPacket implements IClientboundPacket {

	private int val;
	private int val2;
	private float val3;
	private float val4;
	private int val5;
	private float val6;
	private int val7;
	private float val8;
	private float val9;
	private float val10;
	private int val11;
	private int val12;
	private int val13;
	private float val14;
	private float val15;

	public final ClientboundWeaponModifierExPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundWeaponModifierExPacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundWeaponModifierExPacket val3(float val3) {
		this.val3 = val3;
		return this;
	}

	public final float val3() {
		return this.val3;
	}

	public final ClientboundWeaponModifierExPacket val4(float val4) {
		this.val4 = val4;
		return this;
	}

	public final float val4() {
		return this.val4;
	}

	public final ClientboundWeaponModifierExPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	public final ClientboundWeaponModifierExPacket val6(float val6) {
		this.val6 = val6;
		return this;
	}

	public final float val6() {
		return this.val6;
	}

	public final ClientboundWeaponModifierExPacket val7(int val7) {
		this.val7 = val7;
		return this;
	}

	public final int val7() {
		return this.val7;
	}

	public final ClientboundWeaponModifierExPacket val8(float val8) {
		this.val8 = val8;
		return this;
	}

	public final float val8() {
		return this.val8;
	}

	public final ClientboundWeaponModifierExPacket val9(float val9) {
		this.val9 = val9;
		return this;
	}

	public final float val9() {
		return this.val9;
	}

	public final ClientboundWeaponModifierExPacket val10(float val10) {
		this.val10 = val10;
		return this;
	}

	public final float val10() {
		return this.val10;
	}

	public final ClientboundWeaponModifierExPacket val11(int val11) {
		this.val11 = val11;
		return this;
	}

	public final int val11() {
		return this.val11;
	}

	public final ClientboundWeaponModifierExPacket val12(int val12) {
		this.val12 = val12;
		return this;
	}

	public final int val12() {
		return this.val12;
	}

	public final ClientboundWeaponModifierExPacket val13(int val13) {
		this.val13 = val13;
		return this;
	}

	public final int val13() {
		return this.val13;
	}

	public final ClientboundWeaponModifierExPacket val14(float val14) {
		this.val14 = val14;
		return this;
	}

	public final float val14() {
		return this.val14;
	}

	public final ClientboundWeaponModifierExPacket val15(float val15) {
		this.val15 = val15;
		return this;
	}

	public final float val15() {
		return this.val15;
	}

	@Override
	public int packetId() {
		return 452;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.val2);
		buf.writeFloat(this.val3);
		buf.writeFloat(this.val4);
		buf.writeInt(this.val5);
		buf.writeFloat(this.val6);
		buf.writeInt(this.val7);
		buf.writeFloat(this.val8);
		buf.writeFloat(this.val9);
		buf.writeFloat(this.val10);
		buf.writeInt(this.val11);
		buf.writeInt(this.val12);
		buf.writeInt(this.val13);
		buf.writeFloat(this.val14);
		buf.writeFloat(this.val15);
	}
}