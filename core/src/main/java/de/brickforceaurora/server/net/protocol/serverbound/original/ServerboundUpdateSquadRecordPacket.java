package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUpdateSquadRecordPacket implements IServerboundPacket {

    private int win;
    private int draw;
    private int lose;

    public ServerboundUpdateSquadRecordPacket win(final int win) {
        this.win = win;
        return this;
    }

    public int win() {
        return this.win;
    }

    public ServerboundUpdateSquadRecordPacket draw(final int draw) {
        this.draw = draw;
        return this;
    }

    public int draw() {
        return this.draw;
    }

    public ServerboundUpdateSquadRecordPacket lose(final int lose) {
        this.lose = lose;
        return this;
    }

    public int lose() {
        return this.lose;
    }

    @Override
    public int packetId() {
        return 278;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.win = buf.readInt();
        this.draw = buf.readInt();
        this.lose = buf.readInt();
    }
}