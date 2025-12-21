package de.brickforceaurora.server.core;

import de.brickforceaurora.server.net.ClientReference;
import de.brickforceaurora.server.net.ReceiveHandler;
import de.brickforceaurora.server.protocol.*;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class GameServer {

    /* ===== Singleton ===== */

    private static final GameServer INSTANCE = new GameServer();

    public static GameServer getInstance() {
        return INSTANCE;
    }

    private GameServer() {}

    /* ===== Network ===== */

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    /* ===== State ===== */

    private final Object dataLock = new Object();

    public final List<ClientReference> clients = new ArrayList<>();

    private final Queue<Msg2Handle> readQueue = new ConcurrentLinkedQueue<>();
    private final Queue<Msg4Send> writeQueue = new ConcurrentLinkedQueue<>();

    public boolean running = false;

    public byte recvKey = (byte) 0xFF;
    public byte sendKey = (byte) 0xFF;

    /* ===== Startup ===== */

    public void start() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ClientReference client = new ClientReference(ch);
                        synchronized (dataLock) {
                            clients.add(client);
                        }
                        ch.pipeline().addLast(new ReceiveHandler(client));
                    }
                });

        bootstrap.bind(5000).sync();
        running = true;

        System.out.println("[Server] Listening on 0.0.0.0:5000");
    }

    /* ===== Tick ===== */

    public void tick() {
        if (!running)
            return;

        synchronized (dataLock) {
            handleIncoming();
            flushOutgoing();
        }
    }

    /* ===== Networking queues ===== */

    private void handleIncoming() {
        Msg2Handle msg;
        while ((msg = readQueue.poll()) != null) {
            // TEMP: just verify protocol
            System.out.println("Received msgId=" + msg.id());
        }
    }

    private void flushOutgoing() {
        Msg4Send msg;
        while ((msg = writeQueue.poll()) != null) {
            // sending will be wired next
        }
    }

    /* ===== API ===== */

    public void enqueueIncoming(Msg2Handle msg) {
        readQueue.add(msg);
    }

    public void enqueueOutgoing(Msg4Send msg) {
        writeQueue.add(msg);
    }
}
