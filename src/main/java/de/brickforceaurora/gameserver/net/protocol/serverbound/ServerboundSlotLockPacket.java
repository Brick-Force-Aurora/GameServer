package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundSlotLockPacket implements IServerboundPacket {

    private byte slot;
    private byte lck;

    public ServerboundSlotLockPacket slot(final byte slot) {
        this.slot = slot;
        return this;
    }

    public byte slot() {
        return this.slot;
    }

    public ServerboundSlotLockPacket lck(final byte lck) {
        this.lck = lck;
        return this;
    }

    public byte lck() {
        return this.lck;
    }

    @Override
    public int packetId() {
        return 85;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.slot = buffer.readByte();
        this.lck = buffer.readByte();
    }
}