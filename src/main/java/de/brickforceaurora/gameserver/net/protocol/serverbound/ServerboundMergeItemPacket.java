package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundMergeItemPacket implements IServerboundPacket {

	private long src;
	private long dst;
	private String code;

	public final ServerboundMergeItemPacket src(long src) {
		this.src = src;
		return this;
	}

	public final long src() {
		return this.src;
	}

	public final ServerboundMergeItemPacket dst(long dst) {
		this.dst = dst;
		return this;
	}

	public final long dst() {
		return this.dst;
	}

	public final ServerboundMergeItemPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	@Override
	public int packetId() {
		return 357;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.src = buffer.readLongLE();
		this.dst = buffer.readLongLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.code = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.code = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}