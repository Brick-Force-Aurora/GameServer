package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        this.prevPage = buffer.readIntLE();
        this.nextPage = buffer.readIntLE();
        this.index = buffer.readLongLE();
        this.clan = buffer.readIntLE();
    }
}