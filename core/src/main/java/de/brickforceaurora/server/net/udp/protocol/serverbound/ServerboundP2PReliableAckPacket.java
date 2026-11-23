package de.brickforceaurora.server.net.udp.protocol.serverbound;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundP2PReliableAckPacket implements IServerboundPacket {

    private int reliable;

    @Override
    public int packetId() {
        return 27;
    }

    public int reliable() {
        return reliable;
    }

    public int meta() {
        return reliable & 0xFFFF;
    }

    @Override
    public void read(final PacketBuf buffer) {
        reliable = buffer.readInt();
    }

    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
