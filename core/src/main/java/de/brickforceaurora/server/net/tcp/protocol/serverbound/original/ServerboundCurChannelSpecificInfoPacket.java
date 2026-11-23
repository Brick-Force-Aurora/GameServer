package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundCurChannelSpecificInfoPacket implements IServerboundPacket {

    private int val;

    public ServerboundCurChannelSpecificInfoPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 477;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.val = buf.readInt();
    }
}