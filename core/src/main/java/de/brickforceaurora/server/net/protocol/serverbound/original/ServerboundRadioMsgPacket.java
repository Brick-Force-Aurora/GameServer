package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.category = buf.readInt();
		this.msg = buf.readInt();
	}
}