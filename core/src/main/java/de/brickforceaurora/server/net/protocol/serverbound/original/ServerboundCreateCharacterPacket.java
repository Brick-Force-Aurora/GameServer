package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundCreateCharacterPacket implements IServerboundPacket {

    private String nickname;

    public ServerboundCreateCharacterPacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    @Override
    public int packetId() {
        return 411;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.nickname = buf.readString();
    }
}