package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundZombieObserverPacket implements IServerboundPacket {

    private int clientId;

    public ServerboundZombieObserverPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return clientId;
    }

	@Override
	public int packetId() {
		return 549;
	}

	@Override
	public final void read(PacketBuf buf) {
        this.clientId = buf.readInt();
	}
}