package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundWeaponChangePacket implements IServerboundPacket {

    private int slot;
    private long seq;
    private String next;
    private String prev;

    public ServerboundWeaponChangePacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    public ServerboundWeaponChangePacket seq(final long seq) {
        this.seq = seq;
        return this;
    }

    public long seq() {
        return this.seq;
    }

    public ServerboundWeaponChangePacket next(final String next) {
        this.next = next;
        return this;
    }

    public String next() {
        return this.next;
    }

    public ServerboundWeaponChangePacket prev(final String prev) {
        this.prev = prev;
        return this;
    }

    public String prev() {
        return this.prev;
    }

    @Override
    public int packetId() {
        return 414;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.slot = buffer.readIntLE();
        this.seq = buffer.readLongLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.next = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.next = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.prev = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.prev = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}