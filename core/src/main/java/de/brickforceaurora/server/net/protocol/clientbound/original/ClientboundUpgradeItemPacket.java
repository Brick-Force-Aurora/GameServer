package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundUpgradeItemPacket implements IClientboundPacket {

	private int val;
	private long Unnamed0;
	private long Unnamed1;
	private int val4;
	private int val5;

	public final ClientboundUpgradeItemPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundUpgradeItemPacket Unnamed0(long Unnamed0) {
		this.Unnamed0 = Unnamed0;
		return this;
	}

	public final long Unnamed0() {
		return this.Unnamed0;
	}

	public final ClientboundUpgradeItemPacket Unnamed1(long Unnamed1) {
		this.Unnamed1 = Unnamed1;
		return this;
	}

	public final long Unnamed1() {
		return this.Unnamed1;
	}

	public final ClientboundUpgradeItemPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	public final ClientboundUpgradeItemPacket val5(int val5) {
		this.val5 = val5;
		return this;
	}

	public final int val5() {
		return this.val5;
	}

	@Override
	public int packetId() {
		return 354;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeLong(this.Unnamed0);
		buf.writeLong(this.Unnamed1);
		buf.writeInt(this.val4);
		buf.writeInt(this.val5);
	}
}