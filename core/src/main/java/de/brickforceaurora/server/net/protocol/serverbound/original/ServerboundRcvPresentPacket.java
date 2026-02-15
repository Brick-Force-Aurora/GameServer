package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRcvPresentPacket implements IServerboundPacket {

	private long memoSeq;
	private String attached;
	private int option;
	private int val;

	public final ServerboundRcvPresentPacket memoSeq(long memoSeq) {
		this.memoSeq = memoSeq;
		return this;
	}

	public final long memoSeq() {
		return this.memoSeq;
	}

	public final ServerboundRcvPresentPacket attached(String attached) {
		this.attached = attached;
		return this;
	}

	public final String attached() {
		return this.attached;
	}

	public final ServerboundRcvPresentPacket option(int option) {
		this.option = option;
		return this;
	}

	public final int option() {
		return this.option;
	}

	public final ServerboundRcvPresentPacket val(int val) {
		if (val > 255L || val < 0L) {
			throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	@Override
	public int packetId() {
		return 132;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.memoSeq = buf.readLong();
		this.attached = buf.readString();
		this.option = buf.readInt();
		this.val = buf.readUnsignedByte();
	}
}