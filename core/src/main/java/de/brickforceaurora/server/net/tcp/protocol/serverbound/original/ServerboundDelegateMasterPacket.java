package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundDelegateMasterPacket implements IServerboundPacket {

    private int newMaster;

    public ServerboundDelegateMasterPacket newMaster(final int newMaster) {
        this.newMaster = newMaster;
        return this;
    }

    public int newMaster() {
        return this.newMaster;
    }

    @Override
    public int packetId() {
        return 389;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.newMaster = buf.readInt();
    }
}