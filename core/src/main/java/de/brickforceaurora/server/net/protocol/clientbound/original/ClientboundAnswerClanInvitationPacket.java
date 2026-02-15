package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundAnswerClanInvitationPacket implements IClientboundPacket {

	private int val;
	private long val2;
	private int Unnamed0;
	private boolean val4;

	public final ClientboundAnswerClanInvitationPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundAnswerClanInvitationPacket val2(long val2) {
		this.val2 = val2;
		return this;
	}

	public final long val2() {
		return this.val2;
	}

	public final ClientboundAnswerClanInvitationPacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundAnswerClanInvitationPacket val4(boolean val4) {
		this.val4 = val4;
		return this;
	}

	public final boolean val4() {
		return this.val4;
	}

	@Override
	public int packetId() {
		return 196;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeLong(this.val2);
		buf.writeInt(this.Unnamed0);
		buf.writeBoolean(this.val4);
	}
}