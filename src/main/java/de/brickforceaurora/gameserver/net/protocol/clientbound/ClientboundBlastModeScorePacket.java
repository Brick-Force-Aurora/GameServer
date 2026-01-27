package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBlastModeScorePacket implements IClientboundPacket {

	private int Unnamed0;
	private int val2;
	private int val3;

	public final ClientboundBlastModeScorePacket Unnamed0(int Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final int Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundBlastModeScorePacket val2(int val2) {
		this.val2 = val2;
		return this;
	}

	public final int val2() {
		return this.val2;
	}

	public final ClientboundBlastModeScorePacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 294;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.Unnamed0);
		buffer.writeIntLE(this.val2);
		buffer.writeIntLE(this.val3);
	}
}