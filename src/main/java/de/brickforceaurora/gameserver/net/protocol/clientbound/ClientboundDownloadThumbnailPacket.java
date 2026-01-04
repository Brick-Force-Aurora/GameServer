package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;

public final class ClientboundDownloadThumbnailPacket implements IClientboundPacket {

    private int Unnamed0;
    private BufferedImage thumbnail;

    public final ClientboundDownloadThumbnailPacket Unnamed0(int Unnamed0) {
        this.Unnamed0 = Unnamed0;
        return this;
    }

    public final int Unnamed0() {
        return this.Unnamed0;
    }

    public final ClientboundDownloadThumbnailPacket thumbnail(BufferedImage thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public final BufferedImage thumbnail() {
        return this.thumbnail;
    }

    @Override
    public int packetId() {
        return 101;
    }

    @Override
    public final void write(ByteBuf buffer) throws IOException {
        buffer.writeIntLE(this.Unnamed0);
        if (thumbnail != null) {
            FastByteArrayOutputStream output = new FastByteArrayOutputStream();
            ImageIO.write(thumbnail, "png", output);
            buffer.writeIntLE(output.length);
            buffer.writeBytes(output.array, 0, output.length);
        } else {
            buffer.writeIntLE(0);
        }
    }
}