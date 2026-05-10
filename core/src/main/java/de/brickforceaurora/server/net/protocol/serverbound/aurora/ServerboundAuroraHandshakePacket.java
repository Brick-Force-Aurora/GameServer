package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;
import java.math.BigInteger;
import java.security.PublicKey;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import de.brickforceaurora.server.util.Encryption;

public class ServerboundAuroraHandshakePacket implements IServerboundPacket {
    
    private PublicKey clientKey;

    public final ServerboundAuroraHandshakePacket clientKey(PublicKey clientKey) {
        this.clientKey = clientKey;
        return this;
    }

    public final PublicKey clientKey() {
        return this.clientKey;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.SERVERBOUND_AURORA_HANDSHAKE;
    }

    @Override
    public void read(PacketBuf buffer) throws IOException {
        BigInteger modulus = new BigInteger(appendPositiveZero(buffer.readByteArray()));
        BigInteger exponent = new BigInteger(appendPositiveZero(buffer.readByteArray()));
        this.clientKey = Encryption.createPublicKey(modulus, exponent);
    }
    
    private byte[] appendPositiveZero(byte[] bytes) {
        byte[] out = new byte[bytes.length + 1];
        out[0] = 0;
        System.arraycopy(bytes, 0, out, 1, bytes.length);
        return out;
    }
    
    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
