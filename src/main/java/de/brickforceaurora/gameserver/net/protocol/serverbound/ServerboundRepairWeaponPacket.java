package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		this.item = buffer.readLongLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.code = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.code = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		this.buyHow = buffer.readIntLE();
		this.repairFee = buffer.readIntLE();
	}
}