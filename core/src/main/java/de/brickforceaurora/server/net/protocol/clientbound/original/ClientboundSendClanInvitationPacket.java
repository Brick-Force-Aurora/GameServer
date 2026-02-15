package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundSendClanInvitationPacket implements IClientboundPacket {

	private long val;
	private String val2;

	public final ClientboundSendClanInvitationPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundSendClanInvitationPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 194;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
		buf.writeString(this.val2);
	}
}