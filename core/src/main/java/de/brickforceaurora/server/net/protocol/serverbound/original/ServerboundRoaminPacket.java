package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.clientId = buf.readInt();
		this.userType = buf.readInt();
		this.isWebPlayer = buf.readBoolean();
		this.language = buf.readInt();
		this.hash = buf.readString();
	}
}