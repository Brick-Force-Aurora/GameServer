package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void read(ByteBuf buffer) {
		this.win = buffer.readIntLE();
		this.draw = buffer.readIntLE();
		this.lose = buffer.readIntLE();
	}
}