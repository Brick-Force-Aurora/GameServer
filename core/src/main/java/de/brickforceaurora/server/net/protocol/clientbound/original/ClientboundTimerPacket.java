package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTimerPacket implements IClientboundPacket {

	private int remainTime;
	private int playTime;

	public final ClientboundTimerPacket remainTime(int remainTime) {
		this.remainTime = remainTime;
		return this;
	}

	public final int remainTime() {
		return this.remainTime;
	}

	public final ClientboundTimerPacket playTime(int playTime) {
		this.playTime = playTime;
		return this;
	}

	public final int playTime() {
		return this.playTime;
	}

	@Override
	public int packetId() {
		return 66;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.remainTime);
		buf.writeInt(this.playTime);
	}
}
