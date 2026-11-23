package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundSendMemoPacket implements IServerboundPacket {

    private String receiver;
    private String title;
    private String contents;

    public ServerboundSendMemoPacket receiver(final String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String receiver() {
        return this.receiver;
    }

    public ServerboundSendMemoPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundSendMemoPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 126;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.receiver = buf.readString();
        this.title = buf.readString();
        this.contents = buf.readString();
    }
}