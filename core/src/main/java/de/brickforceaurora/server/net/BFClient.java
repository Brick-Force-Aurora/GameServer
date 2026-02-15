package de.brickforceaurora.server.net;

import java.net.InetSocketAddress;
import java.security.PublicKey;
import java.util.Objects;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.util.Encryption;
import me.lauriichan.snowframe.util.Version;
import me.lauriichan.snowframe.util.attribute.Attributable;

public final class BFClient extends Attributable {

    private final io.netty.channel.Channel connection;

    volatile boolean shouldKeepAlive = false;
    volatile long netTime = 0;

    private String identifier;

    private Version version;
    private String name;
    private final String ip;
    private int port;
    private int id;

    private PublicKey encryptionKey;
    private String encryptionChallenge;

    private boolean initialized = false, challanged = false;

    public BFClient(final io.netty.channel.Channel connection) {
        this.connection = connection;
        this.ip = ((InetSocketAddress) connection.remoteAddress()).getAddress().getHostAddress();
    }

    public void init(final Version version, final String name, final int id) {
        if (this.initialized) {
            throw new IllegalStateException("Already initialized");
        }
        this.version = version;
        this.name = name;
        this.identifier = name + '-' + id + '-' + ip;
        this.id = id;
        this.port = 6000 + id;
        this.initialized = true;
    }
    
    public void validateChallenge(final String answeredChallenge) {
        if (this.challanged) {
            throw new IllegalStateException("Already challanged");
        }
        this.challanged = Objects.equals(encryptionChallenge, answeredChallenge);
    }

    public void setupSecret(PublicKey encryptionKey) {
        if (this.encryptionKey != null) {
            throw new IllegalStateException("Already has encryption key");
        }
        this.encryptionKey = encryptionKey;
        this.encryptionChallenge = Encryption.generateSecret(32);
    }

    public PublicKey encryptionKey() {
        return encryptionKey;
    }
    
    public String encryptionChallenge() {
        return encryptionChallenge;
    }
    
    public Version version() {
        return version;
    }

    public boolean isLoggedIn() {
        return initialized;
    }

    public boolean wasChallanged() {
        return challanged;
    }

    public String identifier() {
        return identifier;
    }

    public io.netty.channel.Channel connection() {
        return connection;
    }

    public String name() {
        return name;
    }

    public int port() {
        return port;
    }

    public String ip() {
        return ip;
    }

    public int id() {
        return id;
    }

    public boolean disconnect() {
        if (!connection.isOpen()) {
            return false;
        }
        connection.close();
        return true;
    }

    public void send(final IClientboundPacket packet) {
        connection.writeAndFlush(packet);
    }

    @Override
    public String toString() {
        if (!initialized) {
            return ip;
        }
        return identifier;
    }

}
