package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCurChannelSpecificInfoPacket implements IServerboundPacket {

	private int val;

	public final ServerboundCurChannelSpecificInfoPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 477;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.val = buf.readInt();
	}
}