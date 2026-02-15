package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTeamChangePacket implements IClientboundPacket {

	private int val;
	private int Unnamed0;
	private int val3;

	public final ClientboundTeamChangePacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundTeamChangePacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundTeamChangePacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 81;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeInt(this.Unnamed0);
		buf.writeInt(this.val3);
	}
}