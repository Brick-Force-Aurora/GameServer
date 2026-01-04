package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRadioMsgPacket implements IServerboundPacket {

	private int category;
	private int msg;

	public final ServerboundRadioMsgPacket category(int category) {
		this.category = category;
		return this;
	}

	public final int category() {
		return this.category;
	}

	public final ServerboundRadioMsgPacket msg(int msg) {
		this.msg = msg;
		return this;
	}

	public final int msg() {
		return this.msg;
	}

	@Override
	public int packetId() {
		return 95;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.category = buffer.readIntLE();
		this.msg = buffer.readIntLE();
	}
}