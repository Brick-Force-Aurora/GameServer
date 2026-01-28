package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundClanKickPacket implements IServerboundPacket {

    private int clan;
    private int kick;
    private String nickname;
    private String title;
    private String contents;

    public ServerboundClanKickPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundClanKickPacket kick(final int kick) {
        this.kick = kick;
        return this;
    }

    public int kick() {
        return this.kick;
    }

    public ServerboundClanKickPacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    public ServerboundClanKickPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundClanKickPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 209;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
        this.kick = buffer.readIntLE();
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