package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.item = buf.readLong();
        this.index = buf.readInt();
        this.orgAmount = buf.readInt();
        this.wasKey = buf.readBoolean();
        this.freeCoin = buf.readBoolean();
    }
}