package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundChgCountryFilterPacket implements IServerboundPacket {

    private int country;

    public ServerboundChgCountryFilterPacket country(final int country) {
        this.country = country;
        return this;
    }

    public int country() {
        return this.country;
    }

    @Override
    public int packetId() {
        return 348;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.country = buffer.readIntLE();
    }
}