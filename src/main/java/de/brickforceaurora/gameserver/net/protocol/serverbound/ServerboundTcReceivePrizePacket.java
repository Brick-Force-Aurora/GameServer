package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTcReceivePrizePacket implements IServerboundPacket {

    private long item;
    private int index;
    private int orgAmount;
    private boolean wasKey;
    private boolean freeCoin;

    public ServerboundTcReceivePrizePacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ServerboundTcReceivePrizePacket index(final int index) {
        this.index = index;
        return this;
    }

    public int index() {
        return this.index;
    }

    public ServerboundTcReceivePrizePacket orgAmount(final int orgAmount) {
        this.orgAmount = orgAmount;
        return this;
    }

    public int orgAmount() {
        return this.orgAmount;
    }

    public ServerboundTcReceivePrizePacket wasKey(final boolean wasKey) {
        this.wasKey = wasKey;
        return this;
    }

    public boolean wasKey() {
        return this.wasKey;
    }

    public ServerboundTcReceivePrizePacket freeCoin(final boolean freeCoin) {
        this.freeCoin = freeCoin;
        return this;
    }

    public boolean freeCoin() {
        return this.freeCoin;
    }

    @Override
    public int packetId() {
        return 379;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.item = buffer.readLongLE();
        this.index = buffer.readIntLE();
        this.orgAmount = buffer.readIntLE();
        this.wasKey = buffer.readBoolean();
        this.freeCoin = buffer.readBoolean();
    }
}