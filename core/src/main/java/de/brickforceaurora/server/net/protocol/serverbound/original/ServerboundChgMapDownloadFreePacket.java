package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
        this.downloadFee = buf.readInt();
    }
}