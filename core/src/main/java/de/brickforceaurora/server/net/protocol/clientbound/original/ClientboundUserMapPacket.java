package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.UserMap;

public final class ClientboundUserMapPacket implements IClientboundPacket {

    private static final UserMap[] EMPTY = {};

    private int page;
    private UserMap[] maps = EMPTY;

    public ClientboundUserMapPacket page(final int page) {
        this.page = page;
        return this;
    }

    public int page() {
        return this.page;
    }

    public ClientboundUserMapPacket maps(final UserMap[] maps) {
        this.maps = maps == null ? EMPTY : maps;
        return this;
    }

    public UserMap[] maps() {
        return this.maps;
    }

    @Override
    public int packetId() {
        return 430;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.page);
        buf.writeInt(this.maps.length);
        for (final UserMap map : maps) {
            buf.writeInt(map.slot());
            buf.writeString(map.name());
            buf.writeInt(map.brickCount());
            buf.writeDateTime(map.lastModified());
            buf.writeByte(map.premium());
        }
    }
}