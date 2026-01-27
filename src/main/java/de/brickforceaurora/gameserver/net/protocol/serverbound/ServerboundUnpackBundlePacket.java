package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		this.bundle = buffer.readLongLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.bundleCode = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.bundleCode = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}