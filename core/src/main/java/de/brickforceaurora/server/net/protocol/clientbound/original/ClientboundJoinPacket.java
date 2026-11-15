package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundJoinPacket implements IClientboundPacket {

	public static enum JoinResult {
		TOO_MANY_PLAYERS(-2),
		WRONG_PASSWORD(-3),
		LOADING(-5),
		CANT_JOIN(-6),
		KICKED_OFF_CURRENT_ROOM(-8),
		CLAN_MATCH_SEARCHING(-10);

		private final int id;

		JoinResult(int id){
			this.id = id;
		}

		public int id() {
			return id;
		}
	}

	private int roomIdOrResultId;

	public final ClientboundJoinPacket roomId(int roomId) {
		this.roomIdOrResultId = roomId;
		return this;
	}

	public final ClientboundJoinPacket result(JoinResult result) {
		this.roomIdOrResultId = result.id();
		return this;
	}

	@Override
	public int packetId() {
		return 29;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.roomIdOrResultId);
	}
}