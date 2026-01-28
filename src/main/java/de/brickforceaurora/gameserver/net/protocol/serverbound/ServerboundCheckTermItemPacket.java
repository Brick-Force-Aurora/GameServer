package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.expiring = buffer.readLongLE();
        this.alter = buffer.readLongLE();
    }
}