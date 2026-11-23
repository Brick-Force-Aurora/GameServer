package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.RegisteredMap;

public final class ClientboundAllMapPacket implements IClientboundPacket {

    private static final RegisteredMap[] EMPTY = {};

    private int page;
    private RegisteredMap[] maps = EMPTY;

    public ClientboundAllMapPacket page(final int page) {
        this.page = page;
        return this;
    }

    public int page() {
        return this.page;
    }

    public ClientboundAllMapPacket maps(final RegisteredMap[] maps) {
        this.maps = maps == null ? EMPTY : maps;
        return this;
    }

    public RegisteredMap[] maps() {
        return this.maps;
    }

    @Override
    public int packetId() {
        return 432;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.page);
        buf.writeInt(this.maps.length);
        if (this.maps.length != 0) {
            for (final RegisteredMap map : this.maps) {
                map.toBuffer(buf);
            }
        }
    }
}