package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundCacheBrickDonePacket implements IClientboundPacket {

	private int mapIndex;
	private int skyboxIndex;

	public final ClientboundCacheBrickDonePacket mapIndex(int mapIndex) {
		this.mapIndex = mapIndex;
		return this;
	}

	public final int mapIndex() {
		return this.mapIndex;
	}

	public final ClientboundCacheBrickDonePacket skyboxIndex(int skyboxIndex) {
		this.skyboxIndex = skyboxIndex;
		return this;
	}

	public final int skyboxIndex() {
		return this.skyboxIndex;
	}

	@Override
	public int packetId() {
		return 22;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.mapIndex);
		buf.writeInt(this.skyboxIndex);
	}
}
