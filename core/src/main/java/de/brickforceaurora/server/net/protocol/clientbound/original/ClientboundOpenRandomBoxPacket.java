package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundOpenRandomBoxPacket implements IClientboundPacket {

	private long val;
	private String val2;
	private int val3;

	public final ClientboundOpenRandomBoxPacket val(long val) {
		this.val = val;
		return this;
	}

	public final long val() {
		return this.val;
	}

	public final ClientboundOpenRandomBoxPacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundOpenRandomBoxPacket val3(int val3) {
		this.val3 = val3;
		return this;
	}

	public final int val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 220;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeLong(this.val);
		buf.writeString(this.val2);
		buf.writeInt(this.val3);
	}
}