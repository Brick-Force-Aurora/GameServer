package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundGmCommandUsageLogPacket implements IServerboundPacket {

    private int gmCommand;

    public ServerboundGmCommandUsageLogPacket gmCommand(final int gmCommand) {
        this.gmCommand = gmCommand;
        return this;
    }

    public int gmCommand() {
        return this.gmCommand;
    }

    @Override
    public int packetId() {
        return 516;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.gmCommand = buffer.readIntLE();
    }
}