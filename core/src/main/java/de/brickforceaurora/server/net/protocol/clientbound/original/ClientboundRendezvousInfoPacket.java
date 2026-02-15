package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRendezvousInfoPacket implements IClientboundPacket {

	private int Unnamed0;
	private String val2;
	private int val3;

	public final ClientboundRendezvousInfoPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundRendezvousInfoPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundRendezvousInfoPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 320;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.Unnamed0);
		buf.writeString(this.val2);
		buf.writeInt(this.val3);
	}
}