package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import de.brickforceaurora.server.net.protocol.data.LoginType;
import me.lauriichan.snowframe.util.Version;

public class ServerboundAuroraLoginPacket implements IServerboundPacket {

    private Version version;
    private LoginType loginType;
    private String username;
    private String tokenOrPassword;

    public final ServerboundAuroraLoginPacket version(Version version) {
        this.version = version;
        return this;
    }

    public final Version version() {
        return this.version;
    }

    public final ServerboundAuroraLoginPacket loginType(LoginType loginType) {
        this.loginType = loginType;
        return this;
    }

    public final LoginType loginType() {
        return this.loginType;
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
        this.loginType = LoginType.MANAGER.byMask(buffer.readInt());
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
