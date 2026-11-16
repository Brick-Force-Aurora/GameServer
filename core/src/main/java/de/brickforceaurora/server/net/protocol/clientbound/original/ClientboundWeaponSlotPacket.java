package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundWeaponSlotPacket implements IClientboundPacket {

    private int slot;
    private long item;

    public ClientboundWeaponSlotPacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    public ClientboundWeaponSlotPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    @Override
    public int packetId() {
        return 418;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.slot);
        buf.writeLong(this.item);
    }
}