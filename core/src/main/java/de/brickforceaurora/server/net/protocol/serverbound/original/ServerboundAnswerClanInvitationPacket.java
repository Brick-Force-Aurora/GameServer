package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.memoSeq = buf.readLong();
        this.clan = buf.readInt();
        this.accept = buf.readBoolean();
        this.title = buf.readString();
        this.contents = buf.readString();
    }
}