package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMutePacket implements IServerboundPacket {

    private String nickname;
    private int howlong;

    public ServerboundMutePacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    public ServerboundMutePacket howlong(final int howlong) {
        this.howlong = howlong;
        return this;
    }

    public int howlong() {
        return this.howlong;
    }

    @Override
    public int packetId() {
        return 455;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.nickname = buf.readString();
        this.howlong = buf.readInt();
    }
}