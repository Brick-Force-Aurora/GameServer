package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundEscapeGoalPacket implements IServerboundPacket {

    @Override
    public int packetId() {
        return 523;
    }

    @Override
    public void read(final PacketBuf buf) {}
}