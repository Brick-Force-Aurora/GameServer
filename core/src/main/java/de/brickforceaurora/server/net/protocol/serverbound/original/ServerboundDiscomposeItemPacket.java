package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundDiscomposeItemPacket implements IServerboundPacket {

	private long item;
	private String code;
	private int opt;

	public final ServerboundDiscomposeItemPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundDiscomposeItemPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundDiscomposeItemPacket opt(int opt) {
		this.opt = opt;
		return this;
	}

	public final int opt() {
		return this.opt;
	}

	@Override
	public int packetId() {
		return 316;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.item = buf.readLong();
		this.code = buf.readString();
		this.opt = buf.readInt();
	}
}