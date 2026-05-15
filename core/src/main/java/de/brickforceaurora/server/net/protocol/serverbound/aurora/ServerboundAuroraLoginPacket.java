package de.brickforceaurora.server.net.protocol.serverbound.aurora;

import java.io.IOException;

import de.brickforceaurora.server.net.login.ILoginData;
import de.brickforceaurora.server.net.login.LoginType;
import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import me.lauriichan.snowframe.util.Version;

public class ServerboundAuroraLoginPacket implements IServerboundPacket {

    private Version version;
    private LoginType loginType;
    private String username;
    private ILoginData loginData;

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

    public final ServerboundAuroraLoginPacket loginData(ILoginData loginData) {
        this.loginData = loginData;
        return this;
    }

    public final ILoginData loginData() {
        return this.loginData;
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
        this.loginData = loginType == null ? ILoginData.NONE : switch (loginType) {
        case PASSWORD -> {
            String password = buffer.readString();
            yield new ILoginData.Password(password);
        }
        case TRANSFER_TOKEN -> {
            int originChannel = buffer.readInt();
            String token = buffer.readString();
            yield new ILoginData.Transfer(originChannel, token);
        }
        default -> {
            yield ILoginData.NONE;
        }
        };
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
