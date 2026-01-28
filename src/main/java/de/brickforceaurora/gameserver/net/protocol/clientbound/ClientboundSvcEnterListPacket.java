package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundSvcEnterListPacket implements IClientboundPacket {

    private BFClient[] clients;

    public ClientboundSvcEnterListPacket clients(final BFClient[] clients) {
        this.clients = clients;
        return this;
    }

    public BFClient[] clients() {
        return this.clients;
    }

    @Override
    public int packetId() {
        return 467;
    }

    @Override
    public void write(final ByteBuf buffer) {
        buffer.writeIntLE(this.clients.length);
        for (final BFClient client : this.clients) {
            buffer.writeIntLE(client.id());
            if (client.name().isEmpty()) {
                buffer.writeIntLE(0);
            } else {
                final byte[] bytes = client.name().getBytes(StandardCharsets.UTF_16LE);
                buffer.writeIntLE(bytes.length);
                buffer.writeBytes(bytes);
            }
            buffer.writeIntLE(client.data().xp);
            buffer.writeIntLE(client.data().rank);
        }
    }
}