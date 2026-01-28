package de.brickforceaurora.gameserver.net.protocol.clientbound.emulator;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

import java.io.IOException;

public class ClientboundEmulatorInventoryRequestPacket implements IClientboundPacket {

    private int clientId;

    public final ClientboundEmulatorInventoryRequestPacket clientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public final int clientId() {
        return this.clientId;
    }

    @Override
    public int packetId() {
        return 1003;
    }

    @Override
    public void write(ByteBuf buffer) throws IOException {
        buffer.writeIntLE(this.clientId);
    }
}
