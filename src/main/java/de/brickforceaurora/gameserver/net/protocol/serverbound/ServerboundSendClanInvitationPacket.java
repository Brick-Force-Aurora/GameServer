package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.inviteeSeq = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.invitee = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.invitee = new String(bytes, StandardCharsets.UTF_16LE);
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