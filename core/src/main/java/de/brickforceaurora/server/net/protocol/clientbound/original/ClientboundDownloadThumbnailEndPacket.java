package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundDownloadThumbnailEndPacket implements IClientboundPacket {

	private int val;

	public final ClientboundDownloadThumbnailEndPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 164;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
	}
}