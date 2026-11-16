package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundClanMatchRecordListPacket implements IServerboundPacket {

    private int prevPage;
    private int nextPage;
    private long index;
    private int clan;

    public ServerboundClanMatchRecordListPacket prevPage(final int prevPage) {
        this.prevPage = prevPage;
        return this;
    }

    public int prevPage() {
        return this.prevPage;
    }

    public ServerboundClanMatchRecordListPacket nextPage(final int nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public int nextPage() {
        return this.nextPage;
    }

    public ServerboundClanMatchRecordListPacket index(final long index) {
        this.index = index;
        return this;
    }

    public long index() {
        return this.index;
    }

    public ServerboundClanMatchRecordListPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    @Override
    public int packetId() {
        return 268;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.prevPage = buf.readInt();
        this.nextPage = buf.readInt();
        this.index = buf.readLong();
        this.clan = buf.readInt();
    }
}