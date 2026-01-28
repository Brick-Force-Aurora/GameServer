package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.code = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.code = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.buyHow = buffer.readIntLE();
        this.option = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.receiver = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.receiver = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.title = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.title = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.contents = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.contents = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}