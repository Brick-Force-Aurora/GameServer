package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

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
    public void read(final PacketBuf buf) {
        this.reason = buf.readInt();
        this.clientId = buf.readInt();
        this.contents = buf.readString();
    }
}