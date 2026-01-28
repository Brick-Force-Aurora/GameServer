package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.clan = buffer.readIntLE();
        this.mark = buffer.readIntLE();
        this.ticket = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.ticketCode = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.ticketCode = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}