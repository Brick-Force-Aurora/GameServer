package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
        this.kick = buf.readInt();
        this.nickname = buf.readString();
        this.title = buf.readString();
        this.contents = buf.readString();
    }
}