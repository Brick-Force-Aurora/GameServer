package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import java.awt.image.BufferedImage;
import java.io.IOException;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundSavePacket implements IServerboundPacket {

    private int slot;
    private BufferedImage thumbnail;

    public ServerboundSavePacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    public ServerboundSavePacket thumbnail(final BufferedImage thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public BufferedImage thumbnail() {
        return this.thumbnail;
    }

    @Override
    public int packetId() {
        return 39;
    }

    @Override
    public void read(final PacketBuf buf) throws IOException {
        this.slot = buf.readInt();
        this.thumbnail = buf.readImage();
    }
}