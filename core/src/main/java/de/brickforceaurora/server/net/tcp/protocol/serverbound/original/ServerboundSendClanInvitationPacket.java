package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundSendClanInvitationPacket implements IServerboundPacket {

    private int inviteeSeq;
    private String invitee;
    private String title;
    private String contents;

    public ServerboundSendClanInvitationPacket inviteeSeq(final int inviteeSeq) {
        this.inviteeSeq = inviteeSeq;
        return this;
    }

    public int inviteeSeq() {
        return this.inviteeSeq;
    }

    public ServerboundSendClanInvitationPacket invitee(final String invitee) {
        this.invitee = invitee;
        return this;
    }

    public String invitee() {
        return this.invitee;
    }

    public ServerboundSendClanInvitationPacket title(final String title) {
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public ServerboundSendClanInvitationPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 193;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.inviteeSeq = buf.readInt();
        this.invitee = buf.readString();
        this.title = buf.readString();
        this.contents = buf.readString();
    }
}