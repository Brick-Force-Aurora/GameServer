package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAccusePlayerPacket implements IServerboundPacket {

    private int reason;
    private String nickName;
    private String contents;

    public ServerboundAccusePlayerPacket reason(final int reason) {
        this.reason = reason;
        return this;
    }

    public int reason() {
        return this.reason;
    }

    public ServerboundAccusePlayerPacket nickName(final String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String nickName() {
        return this.nickName;
    }

    public ServerboundAccusePlayerPacket contents(final String contents) {
        this.contents = contents;
        return this;
    }

    public String contents() {
        return this.contents;
    }

    @Override
    public int packetId() {
        return 510;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.reason = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.nickName = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.nickName = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
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