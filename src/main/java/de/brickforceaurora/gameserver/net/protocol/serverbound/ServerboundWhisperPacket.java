package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.listener = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.listener = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.text = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.text = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}