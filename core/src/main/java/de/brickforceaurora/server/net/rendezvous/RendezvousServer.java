package de.brickforceaurora.server.net.rendezvous;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetManager;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.rendezvous.protocol.clientbound.ClientboundP2PPongPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import me.lauriichan.laylib.logger.ISimpleLogger;


public final class RendezvousServer implements AutoCloseable {

    public static final String PORT_PROPERTY = "bfa.rendezvous.port";
    public static final String HOST_PROPERTY = "bfa.rendezvous.host";

    public static final int DEFAULT_PORT = 18890;

    public static final String ATTR_OBSERVED = "rendezvous.observed";
    public static final String ATTR_REPORTED = "rendezvous.reported";
    public static final String ATTR_RELAYED = "rendezvous.relayed";

    private final NetManager<?> netManager;
    private final ISimpleLogger logger;

    private final int port;
    private final String publicHost;

    private final MultiThreadIoEventLoopGroup group = new MultiThreadIoEventLoopGroup(1, new DefaultThreadFactory("rendezvous", true),
        NioIoHandler.newFactory());
    private volatile Channel channel;

    private volatile PeerDirectory directory = new PeerDirectory() {
        @Override
        public List<BFClient> peersOf(final BFClient client) {
            return List.of();
        }

        @Override
        public int slotOf(final BFClient client) {
            return -1;
        }
    };

    // only used by the event loop of the channel
    private final Map<InetSocketAddress, BFClient> clientsByEndpoint = new HashMap<>();

    public RendezvousServer(final NetManager<?> netManager) {
        this.netManager = netManager;
        this.logger = netManager.logger();
        this.port = Integer.getInteger(PORT_PROPERTY, DEFAULT_PORT);
        String host = System.getProperty(HOST_PROPERTY);
        this.publicHost = host == null || host.isBlank() ? null : host.trim();
    }

    public void open() throws InterruptedException {
        if (channel != null) {
            return;
        }
        channel = new Bootstrap().group(group).channel(NioDatagramChannel.class).handler(new RendezvousChannelInit(this)).bind(port).sync()
            .channel();
        logger.info("Rendezvous UDP published at 0.0.0.0:{0}", port);
    }

    @Override
    public void close() {
        if (channel != null) {
            channel.close().awaitUninterruptibly();
            channel = null;
        }
        group.shutdownGracefully();
    }

    public int port() {
        return port;
    }

    public void directory(final PeerDirectory directory) {
        this.directory = directory;
    }

    public String infoHost(final BFClient client) {
        if (isLocal(client)) {
            return "203.0.113.1"; //random ip, avoid sending messages to self when testing locally
        }
        if (publicHost != null) {
            return publicHost;
        }
        return client.connection().localAddress() instanceof InetSocketAddress address ? address.getAddress().getHostAddress()
            : client.ip();
    }

    public int infoPort(final BFClient client) {
        return isLocal(client) ? client.port() : port;
    }

    private static boolean isLocal(final BFClient client) {
        return client.connection().remoteAddress() instanceof InetSocketAddress address && address.getAddress().isLoopbackAddress();
    }

    public static Optional<InetSocketAddress> observed(final BFClient client) {
        return Optional.ofNullable(client.attr(ATTR_OBSERVED, InetSocketAddress.class));
    }

    public void pushPong(final BFClient client) {
        client.attrUnset(ATTR_REPORTED);
        if (!isLocal(client) || channel == null) {
            return;
        }
        channel.eventLoop().execute(() -> register(client, new InetSocketAddress(client.ip(), client.port())));
        schedulePong(client, 750);
        schedulePong(client, 2000);
    }

    NetManager<?> netManager() {
        return netManager;
    }

    ISimpleLogger logger() {
        return logger;
    }

    PeerDirectory directory() {
        return directory;
    }

    BFClient clientAt(final InetSocketAddress endpoint) {
        final BFClient client = clientsByEndpoint.get(endpoint);
        if (client == null) {
            return null;
        }
        if (!client.connection().isOpen() || !endpoint.equals(observed(client).orElse(null))) {
            clientsByEndpoint.remove(endpoint);
            return null;
        }
        return client;
    }

    void register(final BFClient client, final InetSocketAddress endpoint) {
        final InetSocketAddress previous = client.attr(ATTR_OBSERVED, InetSocketAddress.class);
        if (previous != null && !previous.equals(endpoint)) {
            clientsByEndpoint.remove(previous);
        }
        client.attrSet(ATTR_OBSERVED, endpoint);
        clientsByEndpoint.put(endpoint, client);
    }

    private void schedulePong(final BFClient client, final long delayMillis) {
        channel.eventLoop().schedule(() -> {
            if (client.attrHas(ATTR_REPORTED) || !client.connection().isOpen()) {
                return;
            }
            final InetSocketAddress target = new InetSocketAddress(client.ip(), client.port());
            channel.writeAndFlush(pong(target, client.ip(), target.getPort(), 0f));
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    static AddressedEnvelope<IClientboundPacket, InetSocketAddress> pong(final InetSocketAddress target, final String ip, final int port,
        final float time) {
        return new DefaultAddressedEnvelope<>(new ClientboundP2PPongPacket().ip(ip).port(port).time(time).tick(System.nanoTime() / 100),
            target);
    }

}
