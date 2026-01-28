package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAnswerClanInvitationPacket implements IServerboundPacket {

    private long memoSeq;
    private int clan;
    private boolean accept;
    private String title;
    private String contents;

    public ServerboundAnswerClanInvitationPacket memoSeq(final long memoSeq) {
        this.memoSeq = memoSeq;
        return this;
    }

    public long memoSeq() {
        return this.memoSeq;
    }

    public ServerboundAnswerClanInvitationPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundAnswerClanInvitationPacket accept(final boolean accept) {
        this.accept = accept;
        return this;
    }

    public boolean accept() {
        return this.accept;
    }

    public ServerboundAnswerClanInvitationPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundAnswerClanInvitationPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 195;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.memoSeq = buffer.readLongLE();
        this.clan = buffer.readIntLE();
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