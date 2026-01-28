package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAccuseMapPacket implements IServerboundPacket {

    private int reason;
    private int clientId;
    private String contents;

    public ServerboundAccuseMapPacket reason(final int reason) {
        this.reason = reason;
        return this;
    }

    public int reason() {
        return this.reason;
    }

    public ServerboundAccuseMapPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundAccuseMapPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 512;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.reason = buffer.readIntLE();
        this.clientId = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.contents = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.contents = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}