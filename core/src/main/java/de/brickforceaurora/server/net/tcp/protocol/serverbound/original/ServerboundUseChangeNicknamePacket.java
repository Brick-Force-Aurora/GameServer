package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundUseChangeNicknamePacket implements IServerboundPacket {

    private String newNickname;
    private long target;
    private String ticketCode;

    public ServerboundUseChangeNicknamePacket newNickname(final String newNickname) {
        this.newNickname = newNickname;
        return this;
    }

    public String newNickname() {
        return this.newNickname;
    }

    public ServerboundUseChangeNicknamePacket target(final long target) {
        this.target = target;
        return this;
    }

    public long target() {
        return this.target;
    }

    public ServerboundUseChangeNicknamePacket ticketCode(final String ticketCode) {
        this.ticketCode = ticketCode;
        return this;
    }

    public String ticketCode() {
        return this.ticketCode;
    }

    @Override
    public int packetId() {
        return 501;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.newNickname = buf.readString();
        this.target = buf.readLong();
        this.ticketCode = buf.readString();
    }
}