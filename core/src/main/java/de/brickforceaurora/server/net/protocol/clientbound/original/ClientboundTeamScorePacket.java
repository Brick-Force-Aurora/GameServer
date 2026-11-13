package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTeamScorePacket implements IClientboundPacket {

	private int redScore;
	private int blueScore;

	public final ClientboundTeamScorePacket redScore(int redScore) {
		this.redScore = redScore;
		return this;
	}

	public final int redScore() {
		return this.redScore;
	}

	public final ClientboundTeamScorePacket blueScore(int blueScore) {
		this.blueScore = blueScore;
		return this;
	}

	public final int blueScore() {
		return this.blueScore;
	}

	@Override
	public int packetId() {
		return 67;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.redScore);
		buf.writeInt(this.blueScore);
	}
}
