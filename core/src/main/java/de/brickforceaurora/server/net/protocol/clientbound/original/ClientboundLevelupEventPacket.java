package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundLevelupEventPacket implements IClientboundPacket {

	private byte eventType;
	private int clientId;
	private int xp;

	public final ClientboundLevelupEventPacket eventType(byte eventType) {
		this.eventType = eventType;
		return this;
	}

	public final byte eventType() {
		return this.eventType;
	}

	public final ClientboundLevelupEventPacket clientId(int clientId) {
		this.clientId = clientId;
		return this;
	}

	public final int clientId() {
		return this.clientId;
	}

	public final ClientboundLevelupEventPacket xp(int xp) {
		this.xp = xp;
		return this;
	}

	public final int xp() {
		return this.xp;
	}

	@Override
	public int packetId() {
		return 152;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeByte(this.eventType);
		buf.writeInt(this.clientId);
		if (this.eventType == (byte)0) { //write xp for friend or clan level up
			buf.writeInt(this.xp);
		}
	}
}
