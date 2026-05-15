package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ServerboundAuroraHandshakeCompletePacket implements IServerboundPacket {
    
    private byte[] encryptedChallenge;

    public final ServerboundAuroraHandshakeCompletePacket encryptedChallenge(byte[] encryptedChallenge) {
        this.encryptedChallenge = encryptedChallenge;
        return this;
    }

    public final byte[] encryptedChallenge() {
        return this.encryptedChallenge;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.SERVERBOUND_AURORA_HANDSHAKE_COMPLETE;
    }

    @Override
    public void read(PacketBuf buffer) throws IOException {
        this.encryptedChallenge = buffer.readByteArray();
    }
    
    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
