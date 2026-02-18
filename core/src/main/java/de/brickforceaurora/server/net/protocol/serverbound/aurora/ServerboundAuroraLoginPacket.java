package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import me.lauriichan.snowframe.util.Version;

public class ServerboundAuroraLoginPacket implements IServerboundPacket {

    private Version version;
    private byte[] encryptedLoginData;

    public final ServerboundAuroraLoginPacket version(Version version) {
        this.version = version;
        return this;
    }

    public final Version version() {
        return this.version;
    }

    public final ServerboundAuroraLoginPacket encryptedLoginData(byte[] encryptedLoginData) {
        this.encryptedLoginData = encryptedLoginData;
        return this;
    }

    public final byte[] encryptedLoginData() {
        return this.encryptedLoginData;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.SERVERBOUND_AURORA_LOGIN;
    }

    @Override
    public void read(PacketBuf buffer) throws IOException {
        this.version = buffer.readVersion();
        this.encryptedLoginData = buffer.readByteArray();
    }
    
    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
