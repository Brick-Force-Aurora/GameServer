package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundSetWeaponSlotPacket implements IServerboundPacket {

    private int slot;
    private long weapon;

    public ServerboundSetWeaponSlotPacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    public ServerboundSetWeaponSlotPacket weapon(final long weapon) {
        this.weapon = weapon;
        return this;
    }

    public long weapon() {
        return this.weapon;
    }

    @Override
    public int packetId() {
        return 419;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.slot = buf.readInt();
        this.weapon = buf.readLong();
    }
}