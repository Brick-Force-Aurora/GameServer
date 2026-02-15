package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundBuyItemPacket implements IServerboundPacket {

	private String code;
	private int buyHow;
	private int option;
	private int val;
	private boolean needEqup;

	public final ServerboundBuyItemPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundBuyItemPacket buyHow(int buyHow) {
		this.buyHow = buyHow;
		return this;
	}

	public final int buyHow() {
		return this.buyHow;
	}

	public final ServerboundBuyItemPacket option(int option) {
		this.option = option;
		return this;
	}

	public final int option() {
		return this.option;
	}

	public final ServerboundBuyItemPacket val(int val) {
		if (val > 255L || val < 0L) {
			throw new IllegalArgumentException("Value " + val + " is out of bounds of allowed number range of 0 - 255");
		}
		this.val = val;
		return this;
	}

	public final int val() {
		return this.val;
	}

	public final ServerboundBuyItemPacket needEqup(boolean needEqup) {
		this.needEqup = needEqup;
		return this;
	}

	public final boolean needEqup() {
		return this.needEqup;
	}

	@Override
	public int packetId() {
		return 121;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.code = buf.readString();
		this.buyHow = buf.readInt();
		this.option = buf.readInt();
		this.val = buf.readUnsignedByte();
		this.needEqup = buf.readBoolean();
	}
}