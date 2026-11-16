package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundHeartbeatPacket implements IServerboundPacket {

    private int gmFunction;

    public ServerboundHeartbeatPacket gmFunction(final int gmFunction) {
        this.gmFunction = gmFunction;
        return this;
    }

    public int gmFunction() {
        return this.gmFunction;
    }

    @Override
    public int packetId() {
        return 3;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.gmFunction = buf.readInt();
    }
}