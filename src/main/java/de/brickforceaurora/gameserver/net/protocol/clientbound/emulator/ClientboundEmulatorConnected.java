package de.brickforceaurora.gameserver.net.protocol.clientbound.emulator;

import java.io.IOException;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public class ClientboundEmulatorConnected implements IClientboundPacket {

    @Override
    public int packetId() {
        return 1000;
    }

    @Override
    public void write(ByteBuf buffer) throws IOException {}

}
