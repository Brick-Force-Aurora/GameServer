package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundWeaponChangePacket implements IClientboundPacket {

	private int result;
	private int cost;
	private long item;

	public final ClientboundWeaponChangePacket result(int result) {
		this.result = result;
		return this;
	}

	public final int result() {
		return this.result;
	}

	public final ClientboundWeaponChangePacket cost(int cost) {
		this.cost = cost;
		return this;
	}

	public final int cost() {
		return this.cost;
	}

	public final ClientboundWeaponChangePacket item(long item) {
		this.item = item;
		return this;
	}

	public final long item() {
		return this.item;
	}

	@Override
	public int packetId() {
		return 415;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.result);
		buf.writeInt(this.cost);
		buf.writeLong(this.item);
	}
}