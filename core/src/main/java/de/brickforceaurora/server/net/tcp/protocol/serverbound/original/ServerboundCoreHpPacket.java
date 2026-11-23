package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.redHp = buf.readInt();
        this.blueHp = buf.readInt();
    }
}