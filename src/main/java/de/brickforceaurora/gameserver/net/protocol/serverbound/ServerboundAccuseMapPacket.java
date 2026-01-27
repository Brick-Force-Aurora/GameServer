package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundAccuseMapPacket implements IServerboundPacket {

	private int reason;
	private int clientId;
	private String contents;

	public final ServerboundAccuseMapPacket reason(int reason) {
		this.reason = reason;
		return this;
	}

	public final int reason() {
		return this.reason;
	}

	public final ServerboundAccuseMapPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundAccuseMapPacket contents(String contents) {
		this.contents = contents;
		return this;
	}

	public final String contents() {
		return this.contents;
	}

	@Override
	public int packetId() {
		return 512;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.reason = buffer.readIntLE();
		this.clientId = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.contents = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.contents = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}