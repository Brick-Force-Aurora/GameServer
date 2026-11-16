package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundGadgetActionPacket implements IServerboundPacket {

    private int gadget;
    private int action;

    public ServerboundGadgetActionPacket gadget(final int gadget) {
        this.gadget = gadget;
        return this;
    }

    public int gadget() {
        return this.gadget;
    }

    public ServerboundGadgetActionPacket action(final int action) {
        this.action = action;
        return this;
    }

    public int action() {
        return this.action;
    }

    @Override
    public int packetId() {
        return 402;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.gadget = buf.readInt();
        this.action = buf.readInt();
    }
}
