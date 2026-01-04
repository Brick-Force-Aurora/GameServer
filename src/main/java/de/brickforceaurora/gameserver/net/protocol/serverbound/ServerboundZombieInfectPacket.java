package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundZombieInfectPacket implements IServerboundPacket {

	private int brickMan;
	private int zombie;

	public final ServerboundZombieInfectPacket brickMan(int brickMan) {
		this.brickMan = brickMan;
		return this;
	}

	public final int brickMan() {
		return this.brickMan;
	}

	public final ServerboundZombieInfectPacket zombie(int zombie) {
		this.zombie = zombie;
		return this;
	}

	public final int zombie() {
		return this.zombie;
	}

	@Override
	public int packetId() {
		return 540;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.brickMan = buffer.readIntLE();
		this.zombie = buffer.readIntLE();
	}
}