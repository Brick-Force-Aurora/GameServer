package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;

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
    public void read(final ByteBuf buffer) throws IOException {
        this.slot = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.thumbnail = null;
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                final FastByteArrayInputStream input = new FastByteArrayInputStream(bytes);
                this.thumbnail = ImageIO.read(input);
            }
        }
    }
}