package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.slot = buf.readByte();
        this.lck = buf.readByte();
    }
}