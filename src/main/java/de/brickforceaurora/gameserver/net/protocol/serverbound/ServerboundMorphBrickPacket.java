package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
        if (code > 32767L || code < 0L) {
            throw new IllegalArgumentException("Value " + code + " is out of bounds of allowed number range of 0 - 32767");
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
    public void read(final ByteBuf buffer) {
        this.clientId = buffer.readIntLE();
        this.code = buffer.readUnsignedShortLE();
    }
}