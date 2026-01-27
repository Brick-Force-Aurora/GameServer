package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundTeamChangeFailPacket implements IClientboundPacket {

	private int Unnamed0;

	public final ClientboundTeamChangeFailPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	@Override
	public int packetId() {
		return 87;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.Unnamed0);
	}
}