package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundBmBlastPacket implements IClientboundPacket {

	private int Unnamed0;
	private int Unnamed1;

	public final ClientboundBmBlastPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundBmBlastPacket Unnamed1(int Unnamed1) {
		this.Unnamed1 = Unnamed1;
		return this;
	}

	public final int Unnamed1() {
		return this.Unnamed1;
	}

	@Override
	public int packetId() {
		return 284;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.Unnamed0);
		buf.writeInt(this.Unnamed1);
	}
}