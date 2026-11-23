package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundChangeClanNoticePacket implements IServerboundPacket {

    private int clan;
    private String notice;

    public ServerboundChangeClanNoticePacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundChangeClanNoticePacket notice(final String notice) {
        this.notice = notice;
        return this;
    }

    public String notice() {
        return this.notice;
    }

    @Override
    public int packetId() {
        return 225;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
        this.notice = buf.readString();
    }
}