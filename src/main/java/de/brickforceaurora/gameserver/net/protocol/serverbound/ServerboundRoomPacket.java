package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRoomPacket implements IServerboundPacket {

	private int no;

	public final ServerboundRoomPacket no(int no) {
		this.no = no;
		return this;
	}

	public final int no() {
		return this.no;
	}

	@Override
	public int packetId() {
		return 469;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.no = buffer.readIntLE();
	}
}