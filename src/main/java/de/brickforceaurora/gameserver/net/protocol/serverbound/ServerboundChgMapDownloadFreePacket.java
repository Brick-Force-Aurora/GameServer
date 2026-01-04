package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.clientId = buffer.readIntLE();
		this.downloadFee = buffer.readIntLE();
	}
}