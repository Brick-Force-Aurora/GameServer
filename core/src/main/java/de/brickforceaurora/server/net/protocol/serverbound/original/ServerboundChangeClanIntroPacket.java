package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChangeClanIntroPacket implements IServerboundPacket {

    private int clan;
    private String intro;

    public ServerboundChangeClanIntroPacket clan(final int clan) {
        this.clan = clan;
        return this;
    }

    public int clan() {
        return this.clan;
    }

    public ServerboundChangeClanIntroPacket intro(final String intro) {
        this.intro = intro;
        return this;
    }

    public String intro() {
        return this.intro;
    }

    @Override
    public int packetId() {
        return 223;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.clan = buf.readInt();
        this.intro = buf.readString();
    }
}