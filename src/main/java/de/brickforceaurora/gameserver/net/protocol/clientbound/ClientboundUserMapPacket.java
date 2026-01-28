package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.legacy.maps.RegMap;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundUserMapPacket implements IClientboundPacket {

    private int page;
    private int count;
    private int offset;
    private RegMap[] regMaps;

    public ClientboundUserMapPacket page(final int page) {
        this.page = page;
        return this;
    }

    public int page() {
        return this.page;
    }

    public ClientboundUserMapPacket count(final int count) {
        this.count = count;
        return this;
    }

    public int count() {
        return this.count;
    }

    public ClientboundUserMapPacket offset(final int offset) {
        this.offset = offset;
        return this;
    }

    public int offset() {
        return this.offset;
    }

    public ClientboundUserMapPacket regMaps(final RegMap[] regMaps) {
        this.regMaps = regMaps;
        return this;
    }

    public RegMap[] regMaps() {
        return this.regMaps;
    }

    @Override
    public int packetId() {
        return 430;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.page);
        buffer.writeIntLE(this.count);
        for (int i = this.offset; i < this.offset + this.count; i++) {
            final RegMap regMap = regMaps[i];
            buffer.writeIntLE(i); //slot
            if (regMap.getAlias().isEmpty()) {
                buffer.writeIntLE(0);
            } else {
                final byte[] bytes = regMap.getAlias().getBytes(StandardCharsets.UTF_16LE);
                buffer.writeIntLE(bytes.length);
                buffer.writeBytes(bytes);
            }
            buffer.writeIntLE(10000); //brickCount
            buffer.writeIntLE(regMap.getRegisteredDate().getYear());
            buffer.writeByte(regMap.getRegisteredDate().getMonth().getValue());
            buffer.writeByte(regMap.getRegisteredDate().getDayOfMonth());
            buffer.writeByte(regMap.getRegisteredDate().getHour());
            buffer.writeByte(regMap.getRegisteredDate().getMinute());
            buffer.writeByte(regMap.getRegisteredDate().getSecond());
            buffer.writeByte(0);
        }
    }
}