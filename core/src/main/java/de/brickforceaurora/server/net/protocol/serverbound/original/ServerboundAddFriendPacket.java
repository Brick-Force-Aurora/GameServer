package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.friendWannabe = buf.readInt();
	}
}