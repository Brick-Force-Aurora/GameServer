package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCheckTermItemPacket implements IServerboundPacket {

    private long expiring;
    private long alter;

    public ServerboundCheckTermItemPacket expiring(final long expiring) {
        this.expiring = expiring;
        return this;
    }

    public long expiring() {
        return this.expiring;
    }

    public ServerboundCheckTermItemPacket alter(final long alter) {
        this.alter = alter;
        return this;
    }

    public long alter() {
        return this.alter;
    }

    @Override
    public int packetId() {
        return 123;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.expiring = buf.readLong();
        this.alter = buf.readLong();
    }
}