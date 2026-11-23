package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundGadgetActionPacket implements IClientboundPacket {

    private int gadget;
    private int action;

    public ClientboundGadgetActionPacket gadget(final int gadget) {
        this.gadget = gadget;
        return this;
    }

    public int gadget() {
        return this.gadget;
    }

    public ClientboundGadgetActionPacket action(final int action) {
        this.action = action;
        return this;
    }

    public int action() {
        return this.action;
    }

    @Override
    public int packetId() {
        return 403;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.gadget);
        buf.writeInt(this.action);
    }
}
