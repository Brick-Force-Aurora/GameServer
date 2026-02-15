package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChgMapDownloadFreePacket implements IServerboundPacket {

	private int clientId;
	private int downloadFee;

	public final ServerboundChgMapDownloadFreePacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundChgMapDownloadFreePacket downloadFee(int downloadFee) {
		this.downloadFee = downloadFee;
		return this;
	}

	public final int downloadFee() {
		return this.downloadFee;
	}

	@Override
	public int packetId() {
		return 443;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.clientId = buf.readInt();
		this.downloadFee = buf.readInt();
	}
}