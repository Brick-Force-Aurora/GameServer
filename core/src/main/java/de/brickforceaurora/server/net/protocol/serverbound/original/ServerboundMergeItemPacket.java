package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundMergeItemPacket implements IServerboundPacket {

	private long src;
	private long dst;
	private String code;

	public final ServerboundMergeItemPacket src(long src) {
		this.src = src;
		return this;
	}

	public final long src() {
		return this.src;
	}

	public final ServerboundMergeItemPacket dst(long dst) {
		this.dst = dst;
		return this;
	}

	public final long dst() {
		return this.dst;
	}

	public final ServerboundMergeItemPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	@Override
	public int packetId() {
		return 357;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.src = buf.readLong();
		this.dst = buf.readLong();
		this.code = buf.readString();
	}
}