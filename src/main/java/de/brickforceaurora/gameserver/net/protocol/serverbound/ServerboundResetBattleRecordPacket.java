package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundResetBattleRecordPacket implements IServerboundPacket {

    private int target;
    private long ticket;
    private String ticketCode;

    public ServerboundResetBattleRecordPacket target(final int target) {
        this.target = target;
        return this;
    }

    public int target() {
        return this.target;
    }

    public ServerboundResetBattleRecordPacket ticket(final long ticket) {
        this.ticket = ticket;
        return this;
    }

    public long ticket() {
        return this.ticket;
    }

    public ServerboundResetBattleRecordPacket ticketCode(final String ticketCode) {
        this.ticketCode = ticketCode;
        return this;
    }

    public String ticketCode() {
        return this.ticketCode;
    }

    @Override
    public int packetId() {
        return 514;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.target = buffer.readIntLE();
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