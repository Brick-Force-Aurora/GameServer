package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundBmUninstallBombPacket implements IServerboundPacket {

	private int bomb;

	public final ServerboundBmUninstallBombPacket bomb(int bomb) {
		this.bomb = bomb;
		return this;
	}

	public final int bomb() {
		return this.bomb;
	}

	@Override
	public int packetId() {
		return 281;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.bomb = buffer.readIntLE();
	}
}