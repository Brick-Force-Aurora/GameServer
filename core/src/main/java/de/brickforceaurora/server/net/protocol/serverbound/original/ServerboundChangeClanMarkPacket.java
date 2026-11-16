package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChangeClanMarkPacket implements IServerboundPacket {

    private int clan;
    private int mark;
    private long ticket;
    private String ticketCode;

    public ServerboundChangeClanMarkPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundChangeClanMarkPacket mark(final int mark) {
        this.mark = mark;
        return this;
    }

    public int mark() {
        return this.mark;
    }

    public ServerboundChangeClanMarkPacket ticket(final long ticket) {
        this.ticket = ticket;
        return this;
    }

    public long ticket() {
        return this.ticket;
    }

    public ServerboundChangeClanMarkPacket ticketCode(final String ticketCode) {
        this.ticketCode = ticketCode;
        return this;
    }

    public String ticketCode() {
        return this.ticketCode;
    }

    @Override
    public int packetId() {
        return 234;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
        this.mark = buf.readInt();
        this.ticket = buf.readLong();
        this.ticketCode = buf.readString();
    }
}