package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDownloadThumbnailPacket implements IServerboundPacket {

    private boolean userMap;
    private int clientId;

    public ServerboundDownloadThumbnailPacket userMap(final boolean userMap) {
        this.userMap = userMap;
        return this;
    }

    public boolean userMap() {
        return this.userMap;
    }

    public ServerboundDownloadThumbnailPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

	@Override
	public int packetId() {
		return 100;
	}

	@Override
	public final void read(PacketBuf buf) {
        this.userMap = buf.readBoolean();
        this.clientId = buf.readInt();
	}
}