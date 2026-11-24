package de.brickforceaurora.server.net.tcp;

import java.net.InetSocketAddress;
import java.security.PublicKey;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.aurora.ClientboundAuroraDisconnectPacket;
import de.brickforceaurora.server.util.Encryption;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
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

    public void setupSecret(final PublicKey encryptionKey) {
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

    public boolean close() {
        if (!connection.isOpen()) {
            return false;
        }
        connection.close();
        return true;
    }

    public void send(final IClientboundPacket packet) {
        connection.eventLoop().execute(() -> connection.writeAndFlush(packet));
    }

    public void send(final IClientboundPacket... packets) {
        connection.eventLoop().execute(() -> {
            for (final IClientboundPacket packet : packets) {
                connection.write(packet);
            }
            connection.flush();
        });
    }

    public void disconnect(final String message) {
        connection.eventLoop().execute(() -> {
            connection.writeAndFlush(new ClientboundAuroraDisconnectPacket().message(message)).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    // The client only handles the packet on its main thread but reports a closed socket as "Timed out" right away,
                    // so we give it time to show the message and close the connection itself
                    if (future.isSuccess()) {
                        connection.eventLoop().schedule(() -> connection.close(), 300, TimeUnit.MILLISECONDS);
                        return;
                    }
                    connection.close();
                }
            });
        });
    }

    @Override
    public String toString() {
        if (!initialized) {
            return ip;
        }
        return identifier;
    }

}
