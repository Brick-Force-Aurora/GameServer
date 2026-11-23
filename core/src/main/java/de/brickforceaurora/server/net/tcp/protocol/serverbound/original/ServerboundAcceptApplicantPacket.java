package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
        this.member = buf.readInt();
        this.nickname = buf.readString();
        this.accept = buf.readBoolean();
        this.title = buf.readString();
        this.contents = buf.readString();
    }
}