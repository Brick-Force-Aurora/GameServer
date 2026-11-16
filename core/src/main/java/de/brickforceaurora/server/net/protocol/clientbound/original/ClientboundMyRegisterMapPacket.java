package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.RegisteredMap;

public final class ClientboundMyRegisterMapPacket implements IClientboundPacket {

    private static final RegisteredMap[] EMPTY = {};

    private int page;
    private RegisteredMap[] maps = EMPTY;

    public ClientboundMyRegisterMapPacket page(final int page) {
        this.page = page;
        return this;
    }

    public int page() {
        return this.page;
    }

    public ClientboundMyRegisterMapPacket maps(final RegisteredMap[] maps) {
        this.maps = maps == null ? EMPTY : maps;
        return this;
    }

    public RegisteredMap[] maps() {
        return this.maps;
    }

    @Override
    public int packetId() {
        return 428;
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