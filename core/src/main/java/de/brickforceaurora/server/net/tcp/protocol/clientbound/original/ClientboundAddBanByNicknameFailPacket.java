package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAddBanByNicknameFailPacket implements IClientboundPacket {

    private String nickName;

    public ClientboundAddBanByNicknameFailPacket nickName(final String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String nickName() {
        return this.nickName;
    }

    @Override
    public int packetId() {
        return 118;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(0); //unused
        buf.writeString(this.nickName);
    }
}