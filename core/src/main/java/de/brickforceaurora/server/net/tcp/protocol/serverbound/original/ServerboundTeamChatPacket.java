package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundTeamChatPacket implements IServerboundPacket {

    private String text;

    public ServerboundTeamChatPacket text(final String text) {
        this.text = text;
        return this;
    }

    public String text() {
        return this.text;
    }

    @Override
    public int packetId() {
        return 93;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.text = buf.readString();
    }
}