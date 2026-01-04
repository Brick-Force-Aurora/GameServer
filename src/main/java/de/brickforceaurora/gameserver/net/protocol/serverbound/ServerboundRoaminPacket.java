package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ServerboundRoaminPacket implements IServerboundPacket {

	private int clientId;
	private int userType;
	private boolean isWebPlayer;
	private int language;
	private String hash;

	public final ServerboundRoaminPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ServerboundRoaminPacket userType(int userType) {
		this.userType = userType;
		return this;
	}

	public final int userType() {
		return this.userType;
	}

	public final ServerboundRoaminPacket isWebPlayer(boolean isWebPlayer) {
		this.isWebPlayer = isWebPlayer;
		return this;
	}

	public final boolean isWebPlayer() {
		return this.isWebPlayer;
	}

	public final ServerboundRoaminPacket language(int language) {
		this.language = language;
		return this;
	}

	public final int language() {
		return this.language;
	}

	public final ServerboundRoaminPacket hash(String hash) {
		this.hash = hash;
		return this;
	}

	public final String hash() {
		return this.hash;
	}

	@Override
	public int packetId() {
		return 145;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.clientId = buffer.readIntLE();
		this.userType = buffer.readIntLE();
		this.isWebPlayer = buffer.readBoolean();
		this.language = buffer.readIntLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.hash = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.hash = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}