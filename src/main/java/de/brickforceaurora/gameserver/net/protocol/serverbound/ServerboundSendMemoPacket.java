package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
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