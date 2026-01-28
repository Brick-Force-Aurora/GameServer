package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAddBanByNicknamePacket implements IServerboundPacket {

    private String nickname;

    public ServerboundAddBanByNicknamePacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    @Override
    public int packetId() {
        return 112;
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
    }
}