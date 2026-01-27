package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAddFriendPacket implements IServerboundPacket {

	private int friendWannabe;

	public final ServerboundAddFriendPacket friendWannabe(int friendWannabe) {
		this.friendWannabe = friendWannabe;
		return this;
	}

	public final int friendWannabe() {
		return this.friendWannabe;
	}

	@Override
	public int packetId() {
		return 103;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.friendWannabe = buffer.readIntLE();
	}
}