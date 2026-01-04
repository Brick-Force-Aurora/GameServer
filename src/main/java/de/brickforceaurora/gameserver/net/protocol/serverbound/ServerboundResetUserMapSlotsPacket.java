package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundResetUserMapSlotsPacket implements IServerboundPacket {

	private int slot;
	private long item;
	private String itemCode;

	public final ServerboundResetUserMapSlotsPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundResetUserMapSlotsPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundResetUserMapSlotsPacket itemCode(String itemCode) {
		this.itemCode = itemCode;
		return this;
	}

	public final String itemCode() {
		return this.itemCode;
	}

	@Override
	public int packetId() {
		return 405;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.slot = buffer.readIntLE();
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