package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundCoreHpPacket implements IServerboundPacket {

    private int redHp;
    private int blueHp;

    public ServerboundCoreHpPacket redHp(final int redHp) {
        this.redHp = redHp;
        return this;
    }

    public int redHp() {
        return this.redHp;
    }

    public ServerboundCoreHpPacket blueHp(final int blueHp) {
        this.blueHp = blueHp;
        return this;
    }

    public int blueHp() {
        return this.blueHp;
    }

    @Override
    public int packetId() {
        return 342;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.redHp = buffer.readIntLE();
        this.blueHp = buffer.readIntLE();
    }
}