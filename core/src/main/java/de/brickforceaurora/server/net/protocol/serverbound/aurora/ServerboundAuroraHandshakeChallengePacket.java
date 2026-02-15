package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;

public class ServerboundAuroraHandshakeChallengePacket implements IServerboundPacket {
    
    private byte[] encryptedChallenge;

    public final ServerboundAuroraHandshakeChallengePacket encryptedChallenge(byte[] encryptedChallenge) {
        this.encryptedChallenge = encryptedChallenge;
        return this;
    }

    public final byte[] encryptedChallenge() {
        return this.encryptedChallenge;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.SERVERBOUND_AURORA_HANDSHAKE_CHALLENGE;
    }

    @Override
    public void read(PacketBuf buffer) throws IOException {}

}
