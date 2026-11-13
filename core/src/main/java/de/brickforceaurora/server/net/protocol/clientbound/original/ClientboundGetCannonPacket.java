package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundGetCannonPacket implements IClientboundPacket {

	private int shooter;
	private int cannon;

	public final ClientboundGetCannonPacket shooter(int shooter) {
		this.shooter = shooter;
		return this;
	}

	public final int shooter() {
		return this.shooter;
	}

	public final ClientboundGetCannonPacket cannon(int cannon) {
		this.cannon = cannon;
		return this;
	}

	public final int cannon() {
		return this.cannon;
	}

	@Override
	public int packetId() {
		return 159;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.shooter);
		buf.writeInt(this.cannon);
	}
}
