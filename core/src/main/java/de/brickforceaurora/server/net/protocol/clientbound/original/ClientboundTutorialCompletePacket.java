package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundTutorialCompletePacket implements IClientboundPacket {

	private boolean val;
	private byte val2;

	public final ClientboundTutorialCompletePacket val(boolean val) {
		this.val = val;
		return this;
	}

	public final boolean val() {
		return this.val;
	}

	public final ClientboundTutorialCompletePacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 171;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeBoolean(this.val);
		buf.writeByte(this.val2);
	}
}