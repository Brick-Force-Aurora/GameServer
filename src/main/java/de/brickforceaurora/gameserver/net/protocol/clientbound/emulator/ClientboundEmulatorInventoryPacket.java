package de.brickforceaurora.gameserver.net.protocol.clientbound.emulator;

import java.io.IOException;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public class ClientboundEmulatorInventoryPacket implements IClientboundPacket {

    @Override
    public boolean requiresClientId() {
        return true;
    }

    @Override
    public int packetId() {
        return 1003;
    }

    @Override
    public void write(final ByteBuf buffer) throws IOException {}
}
