package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundUseShooterConsumablePacket implements IServerboundPacket {

    private long item;
    private String code;

    public ServerboundUseShooterConsumablePacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundUseShooterConsumablePacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    @Override
    public int packetId() {
        return 329;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.item = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.code = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.code = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}