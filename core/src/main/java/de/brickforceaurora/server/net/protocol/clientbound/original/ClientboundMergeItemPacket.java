package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundMergeItemPacket implements IClientboundPacket {

	private int val;
	private long val2;
	private long val3;
	private int val4;

	public final ClientboundMergeItemPacket val(int val) {
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ClientboundMergeItemPacket val2(long val2) {
		this.val2 = val2;
		return this;
	}

	public final long val2() {
		return this.val2;
	}

	public final ClientboundMergeItemPacket val3(long val3) {
		this.val3 = val3;
		return this;
	}

	public final long val3() {
		return this.val3;
	}

	public final ClientboundMergeItemPacket val4(int val4) {
		this.val4 = val4;
		return this;
	}

	public final int val4() {
		return this.val4;
	}

	@Override
	public int packetId() {
		return 358;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.val);
		buf.writeLong(this.val2);
		buf.writeLong(this.val3);
		buf.writeInt(this.val4);
	}
}