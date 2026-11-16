package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDownClanMemberPacket implements IServerboundPacket {

    private int clan;
    private int member;
    private String nickname;
    private String title;
    private String contents;

    public ServerboundDownClanMemberPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundDownClanMemberPacket member(final int member) {
        this.member = member;
        return this;
    }

    public int member() {
        return this.member;
    }

    public ServerboundDownClanMemberPacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    public ServerboundDownClanMemberPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundDownClanMemberPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 213;
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