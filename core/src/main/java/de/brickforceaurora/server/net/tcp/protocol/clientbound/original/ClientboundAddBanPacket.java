package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAddBanPacket implements IClientboundPacket {

    private int playerId;
    private String nickName;

    public ClientboundAddBanPacket playerId(final int playerId) {
        this.playerId = playerId;
        return this;
    }

    public int playerId() {
        return this.playerId;
    }

    public ClientboundAddBanPacket nickName(final String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String nickName() {
        return this.nickName;
    }

    @Override
    public int packetId() {
        return 106;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.playerId);
        buf.writeString(this.nickName);
    }
}