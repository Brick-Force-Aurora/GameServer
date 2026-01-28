package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundChangeClanIntroPacket implements IServerboundPacket {

    private int clan;
    private String intro;

    public ServerboundChangeClanIntroPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundChangeClanIntroPacket intro(final String intro) {
        this.intro = intro;
        return this;
    }

    public String intro() {
        return this.intro;
    }

    @Override
    public int packetId() {
        return 223;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
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