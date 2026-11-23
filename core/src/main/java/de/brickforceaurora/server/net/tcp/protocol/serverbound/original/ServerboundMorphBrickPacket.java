package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundMorphBrickPacket implements IServerboundPacket {

    private int clientId;
    private int code;

    public ServerboundMorphBrickPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundMorphBrickPacket code(final int code) {
        if (code > 65535L || code < 0L) {
            throw new IllegalArgumentException("Value " + code + " is out of bounds of allowed number range of 0 - 65535");
        }
        this.code = code;
        return this;
    }

    public int code() {
        return this.code;
    }

    @Override
    public int packetId() {
        return 33;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clientId = buf.readInt();
        this.code = buf.readUnsignedShort();
    }
}