package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.bonus = buffer.readIntLE();
	}
}