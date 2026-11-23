package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundAddFriendByNicknamePacket implements IServerboundPacket {

    private String nickname;

    public ServerboundAddFriendByNicknamePacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    @Override
    public int packetId() {
        return 111;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.nickname = buf.readString();
    }
}