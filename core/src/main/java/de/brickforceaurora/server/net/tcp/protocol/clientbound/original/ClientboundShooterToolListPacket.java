package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.ToolSlot;

public final class ClientboundShooterToolListPacket implements IClientboundPacket {

    private ToolSlot[] slots = {};

    public ClientboundShooterToolListPacket slots(final ToolSlot[] slots) {
        this.slots = slots;
        return this;
    }

    public ToolSlot[] slots() {
        return this.slots;
    }

    @Override
    public int packetId() {
        return 462;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.slots.length);
        for (final ToolSlot entry : this.slots) {
            buf.writeByte(entry.slot());
            buf.writeLong(entry.item());
        }
    }
}
