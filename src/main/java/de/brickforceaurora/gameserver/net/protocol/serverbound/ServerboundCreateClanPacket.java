package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCreateClanPacket implements IServerboundPacket {

    private String clanName;

    public ServerboundCreateClanPacket clanName(final String clanName) {
        this.clanName = clanName;
        return this;
    }

    public String clanName() {
        return this.clanName;
    }

    @Override
    public int packetId() {
        return 189;
    }

    @Override
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.clanName = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.clanName = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}