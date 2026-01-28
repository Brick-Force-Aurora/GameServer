package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundChgMapDownloadFreePacket implements IServerboundPacket {

    private int clientId;
    private int downloadFee;

    public ServerboundChgMapDownloadFreePacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundChgMapDownloadFreePacket downloadFee(final int downloadFee) {
        this.downloadFee = downloadFee;
        return this;
    }

    public int downloadFee() {
        return this.downloadFee;
    }

    @Override
    public int packetId() {
        return 443;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
        this.downloadFee = buffer.readIntLE();
    }
}