package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import me.lauriichan.snowframe.util.Version;

public class ServerboundAuroraLoginPacket implements IServerboundPacket {

    private Version version;
    private boolean session;
    private String username;
    private String tokenOrPassword;

    public final ServerboundAuroraLoginPacket version(Version version) {
        this.version = version;
        return this;
    }

    public final Version version() {
        return this.version;
    }

    public final ServerboundAuroraLoginPacket session(boolean session) {
        this.session = session;
        return this;
    }

    public final boolean session() {
        return this.session;
    }

    public final ServerboundAuroraLoginPacket username(String username) {
        this.username = username;
        return this;
    }

    public final String username() {
        return this.username;
    }

    public final ServerboundAuroraLoginPacket tokenOrPassword(String tokenOrPassword) {
        this.tokenOrPassword = tokenOrPassword;
        return this;
    }

    public final String tokenOrPassword() {
        return this.tokenOrPassword;
    }

    @Override
    public int packetId() {
        return ProtocolExtension.SERVERBOUND_AURORA_LOGIN;
    }

    @Override
    public void read(PacketBuf buffer) throws IOException {
        this.version = buffer.readVersion();
        this.session = buffer.readBoolean();
        this.username = buffer.readString();
        this.tokenOrPassword = buffer.readString();
    }
    
    @Override
    public boolean encrypted() {
        return true;
    }
    
    @Override
    public boolean requiresLogIn() {
        return false;
    }

}
