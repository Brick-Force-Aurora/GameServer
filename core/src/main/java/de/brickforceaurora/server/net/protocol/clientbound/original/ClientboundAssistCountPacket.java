package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAssistCountPacket implements IClientboundPacket {

    private int clientId;
    private int assist;
    private int score;

    public ClientboundAssistCountPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundAssistCountPacket assist(final int assist) {
        this.assist = assist;
        return this;
    }

    public int assist() {
        return this.assist;
    }

    public ClientboundAssistCountPacket score(final int score) {
        this.score = score;
        return this;
    }

    public int score() {
        return this.score;
    }

    @Override
    public int packetId() {
        return 185;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeInt(this.assist);
        buf.writeInt(this.score);
    }
}
