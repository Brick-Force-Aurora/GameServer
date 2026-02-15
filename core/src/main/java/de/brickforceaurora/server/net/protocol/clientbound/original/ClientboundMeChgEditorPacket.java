package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMeChgEditorPacket implements IClientboundPacket {

	private int val;
	private boolean val2;

	public final ClientboundMeChgEditorPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundMeChgEditorPacket val2(boolean val2) {
		this.val2 = val2;
		return this;
	}

	public final boolean val2() {
		return this.val2;
	}

	@Override
	public int packetId() {
		return 305;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeBoolean(this.val2);
	}
}