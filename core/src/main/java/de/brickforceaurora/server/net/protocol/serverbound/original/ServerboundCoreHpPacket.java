package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCoreHpPacket implements IServerboundPacket {

	private int redHp;
	private int blueHp;

	public final ServerboundCoreHpPacket redHp(int redHp) {
		this.redHp = redHp;
		return this;
	}

	public final int redHp() {
		return this.redHp;
	}

	public final ServerboundCoreHpPacket blueHp(int blueHp) {
		this.blueHp = blueHp;
		return this;
	}

	public final int blueHp() {
		return this.blueHp;
	}

	@Override
	public int packetId() {
		return 342;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.redHp = buf.readInt();
		this.blueHp = buf.readInt();
	}
}