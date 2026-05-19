package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.RegisteredMap;

public final class ClientboundMyDownloadMapPacket implements IClientboundPacket {

	private static final RegisteredMap[] EMPTY = new RegisteredMap[0];

	private int page;
	private RegisteredMap[] maps = EMPTY;

	public final ClientboundMyDownloadMapPacket page(int page) {
		this.page = page;
		return this;
	}

	public final int page() {
		return this.page;
	}

	public final ClientboundMyDownloadMapPacket maps(RegisteredMap[] maps) {
		this.maps = (maps == null ? EMPTY : maps);
		return this;
	}

	public final RegisteredMap[] maps() {
		return this.maps;
	}

	@Override
	public int packetId() {
		return 426;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.page);
		buf.writeInt(this.maps.length);
		if (this.maps.length != 0) {
			for (RegisteredMap map : this.maps) {
				map.toBuffer(buf);
			}
		}
	}
}