package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundStartKickoutVotePacket implements IClientboundPacket {

    private int result;

    public ClientboundStartKickoutVotePacket result(final int result) {
        this.result = result;
        return this;
    }

    public int result() {
        return this.result;
    }

    @Override
    public int packetId() {
        return 495;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.result);
    }
}
