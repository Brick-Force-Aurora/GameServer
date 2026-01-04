package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundChangeUserPrivateMapAliasPacket implements IServerboundPacket {

	private int slot;
	private String alias;

	public final ServerboundChangeUserPrivateMapAliasPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundChangeUserPrivateMapAliasPacket alias(String alias) {
		this.alias = alias;
		return this;
	}

	public final String alias() {
		return this.alias;
	}

	@Override
	public int packetId() {
		return 54;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.slot = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.alias = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.alias = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}