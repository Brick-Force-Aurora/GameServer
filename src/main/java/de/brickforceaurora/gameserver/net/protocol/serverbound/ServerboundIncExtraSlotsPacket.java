package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundIncExtraSlotsPacket implements IServerboundPacket {

	private long item;
	private String itemCode;

	public final ServerboundIncExtraSlotsPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundIncExtraSlotsPacket itemCode(String itemCode) {
		this.itemCode = itemCode;
		return this;
	}

	public final String itemCode() {
		return this.itemCode;
	}

	@Override
	public int packetId() {
		return 407;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.item = buffer.readLongLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.itemCode = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.itemCode = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}