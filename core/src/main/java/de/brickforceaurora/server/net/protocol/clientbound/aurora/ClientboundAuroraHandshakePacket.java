package de.brickforceaurora.server.net.protocol.clientbound.aurora;

import java.io.IOException;
import java.security.PublicKey;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ClientboundAuroraHandshakePacket implements IClientboundPacket {
    
    private PublicKey serverKey;
    private byte[] encryptedChallenge;

    public final ClientboundAuroraHandshakePacket serverKey(PublicKey serverKey) {
        this.serverKey = serverKey;
        return this;
    }

    public final PublicKey serverKey() {
        return this.serverKey;
    }

    public final ClientboundAuroraHandshakePacket encryptedChallenge(byte[] encryptedChallenge) {
        this.encryptedChallenge = encryptedChallenge;
        return this;
    }

    public final byte[] encryptedChallenge() {
        return this.encryptedChallenge;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.CLIENTBOUND_AURORA_HANDSHAKE;
    }

    @Override
    public void write(final PacketBuf buffer) throws IOException {
        buffer.writeByteArray(serverKey.getEncoded());
        buffer.writeByteArray(encryptedChallenge);
    }

}
