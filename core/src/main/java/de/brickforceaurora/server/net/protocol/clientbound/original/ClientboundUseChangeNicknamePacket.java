package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundUseChangeNicknamePacket implements IClientboundPacket {

	private int val;
	private String val2;
	private String Unnamed0;

	public final ClientboundUseChangeNicknamePacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundUseChangeNicknamePacket val2(String val2) {
		this.val2 = val2;
		return this;
	}

	public final String val2() {
		return this.val2;
	}

	public final ClientboundUseChangeNicknamePacket Unnamed0(String Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final String Unnamed0() {
		return this.Unnamed0;
	}

	@Override
	public int packetId() {
		return 502;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeString(this.val2);
		buf.writeString(this.Unnamed0);
	}
}