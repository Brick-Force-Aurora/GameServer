package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundApplyClanPacket implements IServerboundPacket {

    private int clan;
    private String clanName;

    public ServerboundApplyClanPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundApplyClanPacket clanName(final String clanName) {
        this.clanName = clanName;
        return this;
    }

    public String clanName() {
        return this.clanName;
    }

    @Override
    public int packetId() {
        return 221;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
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