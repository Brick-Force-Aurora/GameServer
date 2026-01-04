package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundTcReceivePrizePacket implements IServerboundPacket {

	private long item;
	private int index;
	private int orgAmount;
	private boolean wasKey;
	private boolean freeCoin;

	public final ServerboundTcReceivePrizePacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundTcReceivePrizePacket index(int index) {
		this.index = index;
		return this;
	}

	public final int index() {
		return this.index;
	}

	public final ServerboundTcReceivePrizePacket orgAmount(int orgAmount) {
		this.orgAmount = orgAmount;
		return this;
	}

	public final int orgAmount() {
		return this.orgAmount;
	}

	public final ServerboundTcReceivePrizePacket wasKey(boolean wasKey) {
		this.wasKey = wasKey;
		return this;
	}

	public final boolean wasKey() {
		return this.wasKey;
	}

	public final ServerboundTcReceivePrizePacket freeCoin(boolean freeCoin) {
		this.freeCoin = freeCoin;
		return this;
	}

	public final boolean freeCoin() {
		return this.freeCoin;
	}

	@Override
	public int packetId() {
		return 379;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.item = buffer.readLongLE();
		this.index = buffer.readIntLE();
		this.orgAmount = buffer.readIntLE();
		this.wasKey = buffer.readBoolean();
		this.freeCoin = buffer.readBoolean();
	}
}