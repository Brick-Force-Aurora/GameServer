package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundChangeUserPrivateMapAliasPacket implements IServerboundPacket {

	private int slot;
	private String alias;

	public final ServerboundChangeUserPrivateMapAliasPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundChangeUserPrivateMapAliasPacket alias(String alias) {
		this.alias = alias;
		return this;
	}

	public final String alias() {
		return this.alias;
	}

	@Override
	public int packetId() {
		return 54;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.slot = buf.readInt();
		this.alias = buf.readString();
	}
}