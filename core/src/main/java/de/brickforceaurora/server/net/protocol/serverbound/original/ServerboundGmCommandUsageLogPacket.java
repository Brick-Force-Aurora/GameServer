package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.gmCommand = buf.readInt();
    }
}