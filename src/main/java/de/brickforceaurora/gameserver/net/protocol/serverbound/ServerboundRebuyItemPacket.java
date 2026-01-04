package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundRebuyItemPacket implements IServerboundPacket {

	private long itemSeq;
	private String code;
	private int buyHow;
	private int option;
	private int val;
	private boolean needEqup;

	public final ServerboundRebuyItemPacket itemSeq(long itemSeq) {
		this.itemSeq = itemSeq;
		return this;
	}

	public final long itemSeq() {
		return this.itemSeq;
	}

	public final ServerboundRebuyItemPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundRebuyItemPacket buyHow(int buyHow) {
		this.buyHow = buyHow;
		return this;
	}

	public final int buyHow() {
		return this.buyHow;
	}

	public final ServerboundRebuyItemPacket option(int option) {
		this.option = option;
		return this;
	}

	public final int option() {
		return this.option;
	}

	public final ServerboundRebuyItemPacket val(int val) {
		if (val > 255L || val < 0L) {
			throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ServerboundRebuyItemPacket needEqup(boolean needEqup) {
		this.needEqup = needEqup;
		return this;
	}

	public final boolean needEqup() {
		return this.needEqup;
	}

	@Override
	public int packetId() {
		return 309;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.itemSeq = buffer.readLongLE();
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
		this.buyHow = buffer.readIntLE();
		this.option = buffer.readIntLE();
		this.val = buffer.readUnsignedByte();
		this.needEqup = buffer.readBoolean();
	}
}