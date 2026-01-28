package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

public final class ClientboundSvcEnterListPacket implements IClientboundPacket {

	private BFClient[] clients;

	public final ClientboundSvcEnterListPacket clients(BFClient[] clients) {
		this.clients = clients;
		return this;
	}

	public final BFClient[] clients() {
		return this.clients;
	}

	@Override
	public int packetId() {
		return 467;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeIntLE(this.clients.length);
		for (BFClient client : this.clients){
			buffer.writeIntLE(client.id());
			if (client.name().isEmpty()) {
				buffer.writeIntLE(0);
			} else {
				byte[] bytes = client.name().getBytes(StandardCharsets.UTF_16LE);
				buffer.writeIntLE(bytes.length);
				buffer.writeBytes(bytes);
			}
			buffer.writeIntLE(client.data().xp);
			buffer.writeIntLE(client.data().rank);
		}
	}
}