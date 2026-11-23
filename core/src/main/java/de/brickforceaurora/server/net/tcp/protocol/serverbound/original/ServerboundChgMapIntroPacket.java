package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundChgMapIntroPacket implements IServerboundPacket {

    private int clientId;
    private String intro;

    public ServerboundChgMapIntroPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundChgMapIntroPacket intro(final String intro) {
        this.intro = intro;
        return this;
    }

    public String intro() {
        return this.intro;
    }

    @Override
    public int packetId() {
        return 441;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
        this.intro = buf.readString();
    }
}