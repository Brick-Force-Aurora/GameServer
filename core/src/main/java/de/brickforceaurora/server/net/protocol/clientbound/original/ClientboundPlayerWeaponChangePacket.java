package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundPlayerWeaponChangePacket implements IClientboundPacket {

	private int clientId;
	private String prev;
	private String next;

	public final ClientboundPlayerWeaponChangePacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundPlayerWeaponChangePacket prev(String prev) {
		this.prev = prev;
		return this;
	}

	public final String prev() {
		return this.prev;
	}

	public final ClientboundPlayerWeaponChangePacket next(String next) {
		this.next = next;
		return this;
	}

	public final String next() {
		return this.next;
	}

	@Override
	public int packetId() {
		return 416;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.clientId);
		buf.writeString(this.prev);
		buf.writeString(this.next);
	}
}