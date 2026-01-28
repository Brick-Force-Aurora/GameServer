package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.slot = buffer.readIntLE();
        this.weapon = buffer.readLongLE();
    }
}