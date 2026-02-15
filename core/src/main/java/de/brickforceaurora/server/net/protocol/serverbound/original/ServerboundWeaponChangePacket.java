package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundWeaponChangePacket implements IServerboundPacket {

	private int slot;
	private long seq;
	private String next;
	private String prev;

	public final ServerboundWeaponChangePacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundWeaponChangePacket seq(long seq) {
		this.seq = seq;
		return this;
	}

	public final long seq() {
		return this.seq;
	}

	public final ServerboundWeaponChangePacket next(String next) {
		this.next = next;
		return this;
	}

	public final String next() {
		return this.next;
	}

	public final ServerboundWeaponChangePacket prev(String prev) {
		this.prev = prev;
		return this;
	}

	public final String prev() {
		return this.prev;
	}

	@Override
	public int packetId() {
		return 414;
	}

	@Override
	public final void read(PacketBuf buf) {
		this.slot = buf.readInt();
		this.seq = buf.readLong();
		this.next = buf.readString();
		this.prev = buf.readString();
	}
}