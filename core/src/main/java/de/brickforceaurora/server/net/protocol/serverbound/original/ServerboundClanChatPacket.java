package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanChatPacket implements IServerboundPacket {

    private String text;

    public ServerboundClanChatPacket text(final String text) {
        this.text = text;
        return this;
    }

    public String text() {
        return this.text;
    }

    @Override
    public int packetId() {
        return 94;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.text = buf.readString();
    }
}