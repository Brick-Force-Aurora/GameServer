package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.reason = buf.readInt();
        this.nickName = buf.readString();
        this.contents = buf.readString();
    }
}