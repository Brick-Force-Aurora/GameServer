package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRadioMsgPacket implements IClientboundPacket {

    private int clientId;
    private int category;
    private int message;

    public ClientboundRadioMsgPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundRadioMsgPacket category(final int category) {
        this.category = category;
        return this;
    }

    public int category() {
        return this.category;
    }

    public ClientboundRadioMsgPacket message(final int message) {
        this.message = message;
        return this;
    }

    public int message() {
        return this.message;
    }

    @Override
    public int packetId() {
        return 96;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeInt(this.category);
        buf.writeInt(this.message);
    }
}
