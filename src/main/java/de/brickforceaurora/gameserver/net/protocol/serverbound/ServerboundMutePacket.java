package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundMutePacket implements IServerboundPacket {

    private String nickname;
    private int howlong;

    public ServerboundMutePacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    public ServerboundMutePacket howlong(final int howlong) {
        this.howlong = howlong;
        return this;
    }

    public int howlong() {
        return this.howlong;
    }

    @Override
    public int packetId() {
        return 455;
    }

    @Override
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.nickname = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.nickname = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.howlong = buffer.readIntLE();
    }
}