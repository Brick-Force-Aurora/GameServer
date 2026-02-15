package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundChangeUserPrivateMapAliasPacket implements IClientboundPacket {

	private int val;
	private byte val2;
	private String val3;

	public final ClientboundChangeUserPrivateMapAliasPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundChangeUserPrivateMapAliasPacket val2(byte val2) {
		this.val2 = val2;
		return this;
	}

	public final byte val2() {
		return this.val2;
	}

	public final ClientboundChangeUserPrivateMapAliasPacket val3(String val3) {
		this.val3 = val3;
		return this;
	}

	public final String val3() {
		return this.val3;
	}

	@Override
	public int packetId() {
		return 55;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeByte(this.val2);
		buf.writeString(this.val3);
	}
}