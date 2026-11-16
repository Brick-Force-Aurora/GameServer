package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundLeaveSquadingPacket implements IServerboundPacket {

    @Override
    public int packetId() {
        return 255;
    }

    @Override
    public void read(final PacketBuf buf) {}
}