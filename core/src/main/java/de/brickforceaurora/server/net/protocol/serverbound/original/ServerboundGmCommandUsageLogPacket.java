package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
	public final void read(PacketBuf buf) {
		this.gmCommand = buf.readInt();
	}
}