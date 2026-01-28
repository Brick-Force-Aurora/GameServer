package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundClanChangeRoomNamePacket implements IClientboundPacket {

    private String val;

    public ClientboundClanChangeRoomNamePacket val(final String val) {
        this.val = val;
        return this;
    }

    public String val() {
        return this.val;
    }

    @Override
    public int packetId() {
        return 564;
    }

    @Override
    public void write(final ByteBuf buffer) {
        if (this.val.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
    }
}