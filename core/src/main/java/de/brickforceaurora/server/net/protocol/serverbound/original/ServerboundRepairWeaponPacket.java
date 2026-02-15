package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRepairWeaponPacket implements IServerboundPacket {

	private long item;
	private String code;
	private int buyHow;
	private int repairFee;

	public final ServerboundRepairWeaponPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundRepairWeaponPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundRepairWeaponPacket buyHow(int buyHow) {
		this.buyHow = buyHow;
		return this;
	}

	public final int buyHow() {
		return this.buyHow;
	}

	public final ServerboundRepairWeaponPacket repairFee(int repairFee) {
		this.repairFee = repairFee;
		return this;
	}

	public final int repairFee() {
		return this.repairFee;
	}

	@Override
	public int packetId() {
		return 351;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.item = buf.readLong();
		this.code = buf.readString();
		this.buyHow = buf.readInt();
		this.repairFee = buf.readInt();
	}
}