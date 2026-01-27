package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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
	public final void read(ByteBuf buffer) {
		this.slot = buffer.readIntLE();
		this.seq = buffer.readLongLE();
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.next = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.next = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.prev = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.prev = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}