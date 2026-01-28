package de.brickforceaurora.gameserver.net.protocol.serverbound.emulator;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public class ServerboundEmulatorRequestInventoryPacket implements IServerboundPacket {

    private int playerId;

    public final ServerboundEmulatorRequestInventoryPacket playerId(int playerId) {
        this.playerId = playerId;
        return this;
    }

    public final int playerId() {
        return this.playerId;
    }

    @Override
    public int packetId() {
        return 1004;
    }

    @Override
    public final void read(ByteBuf buffer) {
        this.playerId = buffer.readIntLE();
    }
}
