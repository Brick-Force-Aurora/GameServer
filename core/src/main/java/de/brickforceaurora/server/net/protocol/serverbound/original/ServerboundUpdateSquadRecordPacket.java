package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUpdateSquadRecordPacket implements IServerboundPacket {

	private int win;
	private int draw;
	private int lose;

	public final ServerboundUpdateSquadRecordPacket win(int win) {
		this.win = win;
		return this;
	}

	public final int win() {
		return this.win;
	}

	public final ServerboundUpdateSquadRecordPacket draw(int draw) {
		this.draw = draw;
		return this;
	}

	public final int draw() {
		return this.draw;
	}

	public final ServerboundUpdateSquadRecordPacket lose(int lose) {
		this.lose = lose;
		return this;
	}

	public final int lose() {
		return this.lose;
	}

	@Override
	public int packetId() {
		return 278;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.win = buf.readInt();
		this.draw = buf.readInt();
		this.lose = buf.readInt();
	}
}