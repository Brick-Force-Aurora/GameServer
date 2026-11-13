package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.DroppedItem;

public final class ClientboundDropItemPacket implements IClientboundPacket {

	private DroppedItem item;

	public final ClientboundDropItemPacket item(DroppedItem item) {
		this.item = item;
		return this;
	}

	public final DroppedItem item() {
		return this.item;
	}

	@Override
	public int packetId() {
		return 527;
	}

	@Override
	public final void write(PacketBuf buf) {
		item.toBuffer(buf);
	}
}
