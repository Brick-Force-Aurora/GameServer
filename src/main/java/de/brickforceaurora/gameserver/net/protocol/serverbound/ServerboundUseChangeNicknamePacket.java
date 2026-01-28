package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.newNickname = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.newNickname = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.target = buffer.readLongLE();
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