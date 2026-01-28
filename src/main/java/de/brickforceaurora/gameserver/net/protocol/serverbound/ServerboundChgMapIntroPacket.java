package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.intro = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.intro = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}