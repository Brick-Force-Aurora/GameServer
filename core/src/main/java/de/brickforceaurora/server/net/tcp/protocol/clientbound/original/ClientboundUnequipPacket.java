package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundUnequipPacket implements IClientboundPacket {

    private int clientId;
    private long item;
    private String code;

    public ClientboundUnequipPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundUnequipPacket item(final long item) {
        this.item = item;
        return this;
    }

    public long item() {
        return this.item;
    }

    public ClientboundUnequipPacket code(final String code) {
        this.code = code;
        return this;
    }

    public String code() {
        return this.code;
    }

    @Override
    public int packetId() {
        return 38;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeLong(this.item);
        buf.writeString(this.code);
    }
}