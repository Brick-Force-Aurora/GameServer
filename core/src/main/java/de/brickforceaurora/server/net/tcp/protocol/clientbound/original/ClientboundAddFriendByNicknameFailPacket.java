package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundAddFriendByNicknameFailPacket implements IClientboundPacket {

    private int Unnamed0;
    private String val2;

    public ClientboundAddFriendByNicknameFailPacket Unnamed0(final int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public int Unnamed0() {
        return this.Unnamed0;
    }

    public ClientboundAddFriendByNicknameFailPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    @Override
    public int packetId() {
        return 117;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.Unnamed0);
        buf.writeString(this.val2);
    }
}