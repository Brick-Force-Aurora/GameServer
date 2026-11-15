package de.brickforceaurora.server.net.rendezvous;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.IBrickForceServer;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.rendezvous.protocol.clientbound.ClientboundP2PPongPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.ISnowFrameApp;
import me.lauriichan.snowframe.SnowFrame;

public final class RendezvousManager<S extends ISnowFrameApp<S> & IBrickForceServer> implements AutoCloseable {

    public static final String PORT_PROPERTY = "bfa.rendezvous.port";
    public static final String HOST_PROPERTY = "bfa.rendezvous.host";

    public static final int DEFAULT_PORT = 18890;

    public static final String ATTR_OBSERVED = "rendezvous.observed";
    public static final String ATTR_REPORTED = "rendezvous.reported";
    public static final String ATTR_RELAYED = "rendezvous.relayed";

    private final SnowFrame<S> snowFrame;
    private final ISimpleLogger logger;

    private final MultiThreadIoEventLoopGroup group;
    private volatile Channel serverChannel;

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

    public RendezvousManager(final SnowFrame<S> frame, final MultiThreadIoEventLoopGroup group) {
        this.snowFrame = frame;
        this.logger = frame.logger();
        this.group = group;
    }

    public final void open(int port) throws InterruptedException {
        if (serverChannel != null) {
            return;
        }
        serverChannel = new Bootstrap().group(group).channel(Epoll.isAvailable() ? EpollDatagramChannel.class : NioDatagramChannel.class)
            .handler(new RendezvousChannelInit<>(this)).bind(port).sync().channel();
        logger.info("Rendezvous UDP published at 0.0.0.0:{0}", port);
    }

    @Override
    public void close() {
        if (serverChannel != null) {
            serverChannel.close().awaitUninterruptibly();
            serverChannel = null;
        }
    }

    public void directory(final PeerDirectory directory) {
        this.directory = directory;
    }

    private static boolean isLocal(final BFClient client) {
        return client.connection().remoteAddress() instanceof InetSocketAddress address && address.getAddress().isLoopbackAddress();
    }

    public static Optional<InetSocketAddress> observed(final BFClient client) {
        return Optional.ofNullable(client.attr(ATTR_OBSERVED, InetSocketAddress.class));
    }

    public void pushPong(final BFClient client) {
        client.attrUnset(ATTR_REPORTED);
        if (!isLocal(client) || serverChannel == null) {
            return;
        }
        serverChannel.eventLoop().execute(() -> register(client, new InetSocketAddress(client.ip(), client.port())));
        schedulePong(client, 750);
        schedulePong(client, 2000);
    }

    public final SnowFrame<S> snowFrame() {
        return snowFrame;
    }

    public final ISimpleLogger logger() {
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
        serverChannel.eventLoop().schedule(() -> {
            if (client.attrHas(ATTR_REPORTED) || !client.connection().isOpen()) {
                return;
            }
            final InetSocketAddress target = new InetSocketAddress(client.ip(), client.port());
            serverChannel.writeAndFlush(pong(target, client.ip(), target.getPort(), 0f));
        }, delayMillis, TimeUnit.MILLISECONDS);
    }

    static AddressedEnvelope<IClientboundPacket, InetSocketAddress> pong(final InetSocketAddress target, final String ip, final int port,
        final float time) {
        return new DefaultAddressedEnvelope<>(new ClientboundP2PPongPacket().ip(ip).port(port).time(time).tick(System.nanoTime() / 100),
            target);
    }

}
