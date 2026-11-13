package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.BrickInfo;
import de.brickforceaurora.server.net.protocol.data.BrickScript;

public final class ClientboundCacheBrickPacket implements IClientboundPacket {

	private BrickInfo[] bricks = new BrickInfo[0];

	public final ClientboundCacheBrickPacket bricks(BrickInfo[] bricks) {
		this.bricks = bricks;
		return this;
	}

	public final BrickInfo[] bricks() {
		return this.bricks;
	}

	@Override
	public int packetId() {
		return 21;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.bricks.length);
		for (BrickInfo brick : this.bricks) {
			brick.toBuffer(buf);
		}
	}
}
