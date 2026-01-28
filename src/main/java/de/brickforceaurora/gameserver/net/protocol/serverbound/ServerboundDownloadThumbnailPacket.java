package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.userMap = buffer.readBoolean();
        this.clientId = buffer.readIntLE();
    }
}