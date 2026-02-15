package de.brickforceaurora.server.net.protocol.serverbound.original;

import java.awt.image.BufferedImage;
import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSavePacket implements IServerboundPacket {

	private int slot;
    private BufferedImage thumbnail;

	public final ServerboundSavePacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
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
	public final void read(PacketBuf buf) throws IOException {
		this.slot = buf.readInt();
		this.thumbnail = buf.readImage();
	}
}