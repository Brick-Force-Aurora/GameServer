package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundKillCountPacket implements IClientboundPacket {

	private int clientId;
	private int kill;

	public final ClientboundKillCountPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundKillCountPacket kill(int kill) {
		this.kill = kill;
		return this;
	}

	public final int kill() {
		return this.kill;
	}

	@Override
	public int packetId() {
		return 69;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeInt(this.kill);
	}
}
