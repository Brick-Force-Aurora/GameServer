package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.ToolSlot;

public final class ClientboundWeaponSlotListPacket implements IClientboundPacket {

    private ToolSlot[] slots = {};

    public ClientboundWeaponSlotListPacket slots(final ToolSlot[] slots) {
        this.slots = slots;
        return this;
    }

    public ToolSlot[] slots() {
        return this.slots;
    }

    @Override
    public int packetId() {
        return 463;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.slots.length);
        for (final ToolSlot entry : this.slots) {
            buf.writeInt(entry.slot());
            buf.writeLong(entry.item());
        }
    }
}
