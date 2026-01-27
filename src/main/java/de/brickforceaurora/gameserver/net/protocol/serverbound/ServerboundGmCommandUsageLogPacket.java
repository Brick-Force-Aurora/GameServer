package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundGmCommandUsageLogPacket implements IServerboundPacket {

	private int gmCommand;

	public final ServerboundGmCommandUsageLogPacket gmCommand(int gmCommand) {
		this.gmCommand = gmCommand;
		return this;
	}

	public final int gmCommand() {
		return this.gmCommand;
	}

	@Override
	public int packetId() {
		return 516;
	}

	@Override
	public final void read(ByteBuf buffer) {
		this.gmCommand = buffer.readIntLE();
	}
}