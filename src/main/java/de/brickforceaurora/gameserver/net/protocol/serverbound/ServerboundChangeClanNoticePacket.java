package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundChangeClanNoticePacket implements IServerboundPacket {

    private int clan;
    private String notice;

    public ServerboundChangeClanNoticePacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundChangeClanNoticePacket notice(final String notice) {
        this.notice = notice;
        return this;
    }

    public String notice() {
        return this.notice;
    }

    @Override
    public int packetId() {
        return 225;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.notice = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.notice = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}