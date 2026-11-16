package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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