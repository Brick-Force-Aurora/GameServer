package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTcOpenPrizeTagPacket implements IServerboundPacket {

	private int chest;
	private int index;
	private boolean freeCoin;

	public final ServerboundTcOpenPrizeTagPacket chest(int chest) {
		this.chest = chest;
		return this;
	}

	public final int chest() {
		return this.chest;
	}

	public final ServerboundTcOpenPrizeTagPacket index(int index) {
		this.index = index;
		return this;
	}

	public final int index() {
		return this.index;
	}

	public final ServerboundTcOpenPrizeTagPacket freeCoin(boolean freeCoin) {
		this.freeCoin = freeCoin;
		return this;
	}

	public final boolean freeCoin() {
		return this.freeCoin;
	}

	@Override
	public int packetId() {
		return 374;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.chest = buffer.readIntLE();
		this.index = buffer.readIntLE();
		this.freeCoin = buffer.readBoolean();
	}
}