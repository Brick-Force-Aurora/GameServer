package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundSlotLockPacket implements IClientboundPacket {

    private byte index;
    private boolean slotLocked;

    public ClientboundSlotLockPacket index(final byte index) {
        this.index = index;
        return this;
    }

    public byte index() {
        return this.index;
    }

    public ClientboundSlotLockPacket slotLocked(final boolean slotLocked) {
        this.slotLocked = slotLocked;
        return this;
    }

    public boolean slotLocked() {
        return this.slotLocked;
    }

    @Override
    public int packetId() {
        return 86;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeByte(this.index);
        buf.writeBoolean(this.slotLocked); //byte?
    }
}