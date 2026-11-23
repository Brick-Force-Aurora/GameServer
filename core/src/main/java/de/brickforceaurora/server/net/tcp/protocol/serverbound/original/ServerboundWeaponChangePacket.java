package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.slot = buf.readInt();
        this.seq = buf.readLong();
        this.next = buf.readString();
        this.prev = buf.readString();
    }
}