package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundUpClanMemberPacket implements IServerboundPacket {

    private int clan;
    private int member;
    private String nickname;
    private String title;
    private String contents;

    public ServerboundUpClanMemberPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundUpClanMemberPacket member(final int member) {
        this.member = member;
        return this;
    }

    public int member() {
        return this.member;
    }

    public ServerboundUpClanMemberPacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    public ServerboundUpClanMemberPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundUpClanMemberPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 211;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
        this.member = buf.readInt();
        this.nickname = buf.readString();
        this.title = buf.readString();
        this.contents = buf.readString();
    }
}