package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundBatchDelBrickPacket implements IServerboundPacket {

    private int[] bricks;

    public ServerboundBatchDelBrickPacket bricks(final int[] bricks) {
        this.bricks = bricks;
        return this;
    }

    public int[] bricks() {
        return bricks;
    }

	@Override
	public int packetId() {
		return 479;
	}

	@Override
	public final void read(PacketBuf buf) {
	    this.bricks = buf.readIntArray();
	}
}