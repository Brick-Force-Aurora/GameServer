package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.brickMan = buf.readInt();
		this.zombie = buf.readInt();
	}
}