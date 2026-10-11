package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundJoinPacket implements IServerboundPacket {

	private int roomId;
	private String password;
	private boolean wasInvited;

	public final ServerboundJoinPacket roomId(int roomId) {
		this.roomId = roomId;
		return this;
	}

	public final int roomId() {
		return this.roomId;
	}

	public final ServerboundJoinPacket password(String password) {
		this.password = password;
		return this;
	}

	public final String password() {
		return this.password;
	}

	public final ServerboundJoinPacket wasInvited(boolean wasInvited) {
		this.wasInvited = wasInvited;
		return this;
	}

	public final boolean wasInvited() {
		return this.wasInvited;
	}

	@Override
	public int packetId() {
		return 28;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.roomId = buf.readInt();
		this.password = buf.readString();
		this.wasInvited = buf.readBoolean();
	}
}