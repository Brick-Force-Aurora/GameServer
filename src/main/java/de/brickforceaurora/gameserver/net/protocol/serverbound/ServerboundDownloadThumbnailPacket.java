package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundDownloadThumbnailPacket implements IServerboundPacket {

    private boolean userMap;
	private int clientId;
	
	public final ServerboundDownloadThumbnailPacket userMap(boolean userMap) {
	    this.userMap = userMap;
	    return this;
	}
	
	public final boolean userMap() {
	    return this.userMap;
	}

	public final ServerboundDownloadThumbnailPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	@Override
	public int packetId() {
		return 100;
	}

	@Override
	public final void read(ByteBuf buffer) {
	    this.userMap = buffer.readBoolean();
		this.clientId = buffer.readIntLE();
	}
}