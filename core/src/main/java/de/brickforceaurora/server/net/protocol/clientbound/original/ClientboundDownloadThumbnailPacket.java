package de.brickforceaurora.server.net.protocol.clientbound.original;

import java.awt.image.BufferedImage;
import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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

    public ClientboundDownloadThumbnailPacket thumbnail(final BufferedImage thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public BufferedImage thumbnail() {
        return this.thumbnail;
    }

	@Override
	public int packetId() {
		return 101;
	}

	@Override
	public final void write(PacketBuf buf) throws IOException {
		buf.writeInt(this.Unnamed0);
		buf.writeImage(this.thumbnail);
	}
}