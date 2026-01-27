package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

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
	public final void write(ByteBuf buffer) {
		buffer.writeBoolean(this.val);
		buffer.writeByte(this.val2);
	}
}