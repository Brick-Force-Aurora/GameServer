package de.brickforceaurora.server.net.protocol.clientbound.aurora;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ClientboundAuroraHandshakePacket implements IClientboundPacket {
    
    private RSAPublicKey serverKey;
    private byte[] encryptedChallenge;

    public final ClientboundAuroraHandshakePacket serverKey(RSAPublicKey serverKey) {
        this.serverKey = serverKey;
        return this;
    }

    public final RSAPublicKey serverKey() {
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
        buffer.writeByteArray(stripLeadingByte(serverKey.getModulus().toByteArray()));
        buffer.writeByteArray(stripLeadingByte(serverKey.getPublicExponent().toByteArray()));
        buffer.writeByteArray(encryptedChallenge);
    }
    
    private byte[] stripLeadingByte(byte[] bytes) {
        if (bytes[0] != 0) {
            return bytes;
        }
        byte[] out = new byte[bytes.length - 1];
        System.arraycopy(bytes, 1, out, 0, out.length);
        return out;
    }

}
