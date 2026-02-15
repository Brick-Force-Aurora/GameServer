package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChgSquadOptionPacket implements IServerboundPacket {

	private int wannaPlayMap;
	private int wannaPlayMode;
	private int maxPlayers;

	public final ServerboundChgSquadOptionPacket wannaPlayMap(int wannaPlayMap) {
		this.wannaPlayMap = wannaPlayMap;
		return this;
	}

	public final int wannaPlayMap() {
		return this.wannaPlayMap;
	}

	public final ServerboundChgSquadOptionPacket wannaPlayMode(int wannaPlayMode) {
		this.wannaPlayMode = wannaPlayMode;
		return this;
	}

	public final int wannaPlayMode() {
		return this.wannaPlayMode;
	}

	public final ServerboundChgSquadOptionPacket maxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
		return this;
	}

	public final int maxPlayers() {
		return this.maxPlayers;
	}

	@Override
	public int packetId() {
		return 259;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.wannaPlayMap = buf.readInt();
		this.wannaPlayMode = buf.readInt();
		this.maxPlayers = buf.readInt();
	}
}