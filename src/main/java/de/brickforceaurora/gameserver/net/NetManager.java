package de.brickforceaurora.gameserver.net;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Stream;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import de.brickforceaurora.gameserver.net.protocol.IPacket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SignalModule;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.signal.SignalManager;

public final class NetManager implements AutoCloseable {

    private static final long TIMEOUT_TIME = TimeUnit.SECONDS.toNanos(5);

    public final AtomicInteger nextClientId = new AtomicInteger(0);

    private final NioEventLoopGroup mainGroup = new NioEventLoopGroup(1);
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    private final ServerBootstrap bootstrap = new ServerBootstrap();
    private volatile Channel serverChannel;

    private final SnowFrame<GameServerApp> snowFrame;

    private final SignalManager signalManager;
    private final ISimpleLogger logger;

    private final ObjectList<BFClient> clients = ObjectLists.synchronize(new ObjectArrayList<>());

    private final ObjectList<NetHandlerContainer> containers = ObjectLists.synchronize(new ObjectArrayList<>());

    private volatile long netTime = 0;

    public NetManager(final SnowFrame<GameServerApp> frame) {
        this.snowFrame = frame;
        this.logger = frame.logger();
        this.signalManager = frame.module(SignalModule.class).signalManager();
        registerListenerExtensions();
    }

    /*
     * Heartbeat related
     */

    public void tick(final long delta) {
        netTime += delta;
        BFClient client;
        for (int i = 0; i < clients.size(); i++) {
            client = clients.get(i);
            if (client.shouldKeepAlive) {
                client.shouldKeepAlive = false;
                client.netTime = netTime;
                continue;
            }
            if (netTime - client.netTime > TIMEOUT_TIME) {
                logger.debug("Client timedout: {0}", client.ip());
                // We first remove the client from the clientList
                clientDisconnected(client);
                // And then actually disconnect them
                client.disconnect();
                // Decrease index as client has been removed
                i--;
            }
        }
    }

    public void keepClientAlive(final BFClient client) {
        client.shouldKeepAlive = true;
    }

    /*
     * Getter
     */

    public SnowFrame<GameServerApp> snowFrame() {
        return snowFrame;
    }

    public SignalManager signalManager() {
        return signalManager;
    }

    public ISimpleLogger logger() {
        return logger;
    }

    /*
     * Client related
     */

    public Optional<BFClient> clientById(final int clientId) {
        if (clients.isEmpty()) {
            return Optional.empty();
        }
        return clients.stream().filter(client -> client.isLoggedIn() && client.id() == clientId).findFirst();
    }

    public Stream<BFClient> activeClients() {
        return clients.stream().filter(BFClient::isLoggedIn);
    }

    public void broadcast(final IClientboundPacket packet) {
        activeClients().forEach(client -> client.send(packet));
    }

    public void broadcast(final Predicate<BFClient> predicate, final IClientboundPacket packet) {
        activeClients().filter(predicate).forEach(client -> client.send(packet));
    }

    /*
     * Listener related
     */

    public NetHandlerContainer registerListener(final INetListener listener) {
        for (int i = 0; i < containers.size(); i++) {
            final NetHandlerContainer container = containers.get(i);
            if (Objects.equals(container.listener(), listener)) {
                return container;
            }
        }
        final NetHandlerContainer container = listener.newContainer();
        containers.add(container);
        return container;
    }

    public boolean unregisterListener(final NetHandlerContainer container) {
        return containers.remove(container);
    }

    void registerListenerExtensions() {
        snowFrame.extension(INetListener.class, true).callInstances(this::registerListener);
    }

    <P extends IPacket> boolean handlePacket(final BFClient client, final P packet) {
        if (containers.isEmpty()) {
            return false;
        }
        final NetContext<P> context = new NetContext<>(this, client, packet);
        for (final NetHandlerContainer container : containers) {
            if (!container.supports(packet.packetId())) {
                continue;
            }
            container.handlePacket(context);
        }
        return context.intercepts();
    }

    /*
     * Server logic related
     */

    public void open() throws InterruptedException {
        if (serverChannel != null) {
            return;
        }
        bootstrap.group(mainGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new BFChannelInit(this));
        serverChannel = bootstrap.bind(5000).sync().channel();
        logger.info("Listening on 0.0.0.0:5000");
        signalManager.call(new NetSignal.ServerStarted(this));
    }

    @Override
    public void close() throws Exception {
        if (serverChannel == null || !serverChannel.isOpen()) {
            return;
        }

        // Disconnect all clients before closing
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).disconnect();
        }

        serverChannel.close().awaitUninterruptibly();
        mainGroup.close();
        workerGroup.close();
    }

    void clientConnected(final BFClient client) {
        if (clients.contains(client)) {
            return;
        }
        // Set client net time to current net time
        client.netTime = netTime;
        clients.add(client);
        logger.info("Client connected: {0}", client);
        signalManager.call(new NetSignal.ClientConnected(this, client));
    }

    void clientDisconnected(final BFClient client) {
        if (!clients.remove(client)) {
            return;
        }
        logger.info("Client disconnected: {0}", client);
        signalManager.call(new NetSignal.ClientDisconnected(this, client));
    }

}
