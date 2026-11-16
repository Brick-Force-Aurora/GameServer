package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundWhisperPacket implements IServerboundPacket {

    private String listener;
    private String text;

    public ServerboundWhisperPacket listener(final String listener) {
        this.listener = listener;
        return this;
    }

    public String listener() {
        return this.listener;
    }

    public ServerboundWhisperPacket text(final String text) {
        this.text = text;
        return this;
    }

    public String text() {
        return this.text;
    }

    @Override
    public int packetId() {
        return 26;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.listener = buf.readString();
        this.text = buf.readString();
    }
}