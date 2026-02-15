package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUnpackBundlePacket implements IServerboundPacket {

	private long bundle;
	private String bundleCode;

	public final ServerboundUnpackBundlePacket bundle(long bundle) {
		this.bundle = bundle;
		return this;
	}

	public final long bundle() {
		return this.bundle;
	}

	public final ServerboundUnpackBundlePacket bundleCode(String bundleCode) {
		this.bundleCode = bundleCode;
		return this;
	}

	public final String bundleCode() {
		return this.bundleCode;
	}

	@Override
	public int packetId() {
		return 360;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.bundle = buf.readLong();
		this.bundleCode = buf.readString();
	}
}