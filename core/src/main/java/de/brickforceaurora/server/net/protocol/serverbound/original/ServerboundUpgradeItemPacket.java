package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundUpgradeItemPacket implements IServerboundPacket {

	private long item;
	private String code;
	private long upgrader;
	private String upgradercode;
	private int prop;

	public final ServerboundUpgradeItemPacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	public final ServerboundUpgradeItemPacket code(String code) {
		this.code = code;
		return this;
	}

	public final String code() {
		return this.code;
	}

	public final ServerboundUpgradeItemPacket upgrader(long upgrader) {
		this.upgrader = upgrader;
		return this;
	}

	public final long upgrader() {
		return this.upgrader;
	}

	public final ServerboundUpgradeItemPacket upgradercode(String upgradercode) {
		this.upgradercode = upgradercode;
		return this;
	}

	public final String upgradercode() {
		return this.upgradercode;
	}

	public final ServerboundUpgradeItemPacket prop(int prop) {
		this.prop = prop;
		return this;
	}

	public final int prop() {
		return this.prop;
	}

	@Override
	public int packetId() {
		return 353;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.item = buf.readLong();
		this.code = buf.readString();
		this.upgrader = buf.readLong();
		this.upgradercode = buf.readString();
		this.prop = buf.readInt();
	}
}