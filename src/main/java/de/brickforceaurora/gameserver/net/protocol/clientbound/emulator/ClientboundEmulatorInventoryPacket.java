package de.brickforceaurora.gameserver.net.protocol.clientbound.emulator;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

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
    public void write(ByteBuf buffer) throws IOException {}
}
