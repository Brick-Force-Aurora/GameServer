package de.brickforceaurora.gameserver.net;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicReference;

import de.brickforceaurora.gameserver.channel.Channel;
import de.brickforceaurora.gameserver.legacy.data.ClientData;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import de.brickforceaurora.gameserver.util.Attribute;
import me.lauriichan.snowframe.util.attribute.Attributable;

public final class BFClient extends Attributable {

    public static final Attribute<Integer> NEXT_CHANNEL_ATTR = Attribute.of("NEXT_CHANNEL", Integer.class);

    public final AtomicReference<Channel> channel = new AtomicReference<>();

    private final io.netty.channel.Channel connection;

    private final ClientData data;

    volatile boolean shouldKeepAlive = false;
    volatile long netTime = 0;

    private String identifier;

    private String name;
    private final String ip;
    private int port;
    private int id;

    private boolean initialized = false;

    public BFClient(final io.netty.channel.Channel connection) {
        this.connection = connection;
        this.ip = ((InetSocketAddress) connection.remoteAddress()).getAddress().getHostAddress();
        this.data = new ClientData(this);
    }

    public void init(final String name, final int id) {
        if (this.initialized) {
            throw new IllegalStateException("Already initialized");
        }
        this.name = name;
        this.identifier = name + '-' + id + '-' + ip;
        this.id = id;
        this.port = 6000 + id;
        this.initialized = true;
    }

    public boolean isLoggedIn() {
        return initialized;
    }

    public String identifier() {
        return identifier;
    }

    public io.netty.channel.Channel connection() {
        return connection;
    }

    public Channel channel() {
        return channel.get();
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

    public ClientData data() {
        return data;
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
