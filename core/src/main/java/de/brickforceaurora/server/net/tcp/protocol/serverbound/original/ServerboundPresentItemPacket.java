package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundPresentItemPacket implements IServerboundPacket {

    private String code;
    private int buyHow;
    private int option;
    private String receiver;
    private String title;
    private String contents;

    public ServerboundPresentItemPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    public ServerboundPresentItemPacket buyHow(final int buyHow) {
        this.buyHow = buyHow;
        return this;
    }

    public int buyHow() {
        return this.buyHow;
    }

    public ServerboundPresentItemPacket option(final int option) {
        this.option = option;
        return this;
    }

    public int option() {
        return this.option;
    }

    public ServerboundPresentItemPacket receiver(final String receiver) {
        this.receiver = receiver;
        return this;
    }

    public String receiver() {
        return this.receiver;
    }

    public ServerboundPresentItemPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundPresentItemPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 128;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.code = buf.readString();
        this.buyHow = buf.readInt();
        this.option = buf.readInt();
        this.receiver = buf.readString();
        this.title = buf.readString();
        this.contents = buf.readString();
    }
}