package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSerialBonusPacket implements IServerboundPacket {

	private int bonus;

	public final ServerboundSerialBonusPacket bonus(int bonus) {
		this.bonus = bonus;
		return this;
	}

	public final int bonus() {
		return this.bonus;
	}

	@Override
	public int packetId() {
		return 82;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.bonus = buf.readInt();
	}
}