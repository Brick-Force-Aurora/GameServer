package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.target = buf.readInt();
        this.ticket = buf.readLong();
        this.ticketCode = buf.readString();
    }
}