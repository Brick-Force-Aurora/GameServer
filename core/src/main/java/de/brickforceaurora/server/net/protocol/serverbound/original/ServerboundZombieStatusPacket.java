package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundZombieStatusPacket implements IServerboundPacket {

	private int status;
	private int time;
	private int cntDn;

	public final ServerboundZombieStatusPacket status(int status) {
		this.status = status;
		return this;
	}

	public final int status() {
		return this.status;
	}

	public final ServerboundZombieStatusPacket time(int time) {
		this.time = time;
		return this;
	}

	public final int time() {
		return this.time;
	}

	public final ServerboundZombieStatusPacket cntDn(int cntDn) {
		this.cntDn = cntDn;
		return this;
	}

	public final int cntDn() {
		return this.cntDn;
	}

	@Override
	public int packetId() {
		return 547;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.status = buf.readInt();
		this.time = buf.readInt();
		this.cntDn = buf.readInt();
	}
}