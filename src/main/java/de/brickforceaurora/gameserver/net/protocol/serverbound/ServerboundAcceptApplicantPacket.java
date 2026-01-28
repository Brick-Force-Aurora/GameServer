package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAcceptApplicantPacket implements IServerboundPacket {

    private int clan;
    private int member;
    private String nickname;
    private boolean accept;
    private String title;
    private String contents;

    public ServerboundAcceptApplicantPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundAcceptApplicantPacket member(final int member) {
        this.member = member;
        return this;
    }

    public int member() {
        return this.member;
    }

    public ServerboundAcceptApplicantPacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    public ServerboundAcceptApplicantPacket accept(final boolean accept) {
        this.accept = accept;
        return this;
    }

    public boolean accept() {
        return this.accept;
    }

    public ServerboundAcceptApplicantPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundAcceptApplicantPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 217;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
        this.member = buffer.readIntLE();
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
        this.accept = buffer.readBoolean();
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