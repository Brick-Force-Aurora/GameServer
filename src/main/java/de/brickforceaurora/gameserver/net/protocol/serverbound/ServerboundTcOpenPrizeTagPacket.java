package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTcOpenPrizeTagPacket implements IServerboundPacket {

    private int chest;
    private int index;
    private boolean freeCoin;

    public ServerboundTcOpenPrizeTagPacket chest(final int chest) {
        this.chest = chest;
        return this;
    }

    public int chest() {
        return this.chest;
    }

    public ServerboundTcOpenPrizeTagPacket index(final int index) {
        this.index = index;
        return this;
    }

    public int index() {
        return this.index;
    }

    public ServerboundTcOpenPrizeTagPacket freeCoin(final boolean freeCoin) {
        this.freeCoin = freeCoin;
        return this;
    }

    public boolean freeCoin() {
        return this.freeCoin;
    }

    @Override
    public int packetId() {
        return 374;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.chest = buffer.readIntLE();
        this.index = buffer.readIntLE();
        this.freeCoin = buffer.readBoolean();
    }
}