package de.brickforceaurora.gameserver.legacy.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import de.brickforceaurora.gameserver.legacy.channel.ChannelManager;
import de.brickforceaurora.gameserver.legacy.channel.ChannelReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientStatus;
import de.brickforceaurora.gameserver.legacy.handler.ChannelHandlers;
import de.brickforceaurora.gameserver.legacy.handler.InventoryHandlers;
import de.brickforceaurora.gameserver.legacy.handler.LoginHandlers;
import de.brickforceaurora.gameserver.legacy.handler.MapHandlers;
import de.brickforceaurora.gameserver.legacy.handler.MatchHandlers;
import de.brickforceaurora.gameserver.legacy.handler.MessageDispatcher;
import de.brickforceaurora.gameserver.legacy.handler.RoomHandlers;
import de.brickforceaurora.gameserver.legacy.maps.RegMapManager;
import de.brickforceaurora.gameserver.legacy.match.MatchData;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.Msg4Send;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;
import de.brickforceaurora.gameserver.legacy.protocol.SendType;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SnowFrame;

public final class GameServerLogic {

    private final Object dataLock = new Object();

    public final List<ClientReference> clientList = new ArrayList<>();
    private final Queue<MsgReference> readQueue = new ArrayDeque<>();
    private final Queue<MsgReference> writeQueue = new ArrayDeque<>();

    private final MessageDispatcher dispatcher = new MessageDispatcher();

    public boolean debugHandle = true;
    public boolean debugSend = true;
    public boolean debugPing = false;

    public int curSeq = 0;

    public boolean serverCreated = false;
    public boolean isSteam = false;

    public byte recvKey = (byte) 0xFF;
    public byte sendKey = (byte) 0xFF;

    private boolean waitForShutDown = false;
    public float killLogTimer = 0f;

    public ChannelManager channelManager = new ChannelManager();

    private final ISimpleLogger logger;

    /* ===== Startup ===== */

    public GameServerLogic(final SnowFrame<?> frame) {
        this.logger = frame.logger();
        registerHandlers();
        serverCreated = true;
    }

    public ISimpleLogger logger() {
        return logger;
    }

    public void tick(final float deltaTime) {
        if (!serverCreated) {
            return;
        }

        synchronized (dataLock) {
            if (waitForShutDown && (clientList.isEmpty() || isSteam)) {
                shutdownFinally();
                return;
            }

            killLogTimer += deltaTime;

            handleDeadClients(deltaTime);
            handleMessages();
            sendMessages();
        }
    }

    public void start() throws InterruptedException {

        final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final EventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(final SocketChannel ch) {
                final ClientReference client = new ClientReference(ch);

                //ch.pipeline().addLast(new ReceiveHandler(client));

                handleClientAccepted(client);
            }
        });

        bootstrap.bind(5000).sync();

        //Pulls all loaded RegMaps into the server should be refactored for the MapServer
        RegMapManager.getInstance().loadMaps();
        logger.debug("RegMaps: " + RegMapManager.getInstance().getMaps().size());

        serverCreated = true;
        logger.info("Listening on 0.0.0.0:5000");
    }

    /* =========================
       Queues
       ========================= */

    public void enqueueIncoming(final MsgReference msg) {
        synchronized (dataLock) {
            readQueue.add(msg);
        }
    }

    public void say(final MsgReference msg) {
        synchronized (dataLock) {
            writeQueue.add(msg);
        }
    }

    public void sayInstant(final MsgReference msg) {
        synchronized (dataLock) {
            writeQueue.add(msg);
            sendMessages();
        }
    }

    public void clearBuffers() {
        synchronized (dataLock) {
            writeQueue.clear();
            readQueue.clear();
        }
    }

    /* =========================
       Client accept
       ========================= */

    public boolean handleClientAccepted(final ClientReference client) {
        synchronized (dataLock) {
            clientList.add(client);
            sendConnected(client);
            return true;
        }
    }

    /* =========================
       Message handling (like C#)
       ========================= */

    private void registerHandlers() {
        LoginHandlers.register(dispatcher);
        RoomHandlers.register(dispatcher);
        MapHandlers.register(dispatcher);
        InventoryHandlers.register(dispatcher);
        ChannelHandlers.register(dispatcher);
        MatchHandlers.register(dispatcher);
    }

    private void handleMessages() {
        if (readQueue.isEmpty()) {
            return;
        }

        final MsgReference msgRef = readQueue.peek();

        try {
            if (debugHandle && msgRef.client != null) {
                logger.debug("Processing message ID {0} from client {1}", msgRef.msg.id(), msgRef.client.GetIdentifier());
            }

            dispatcher.dispatch(this, msgRef);

        } catch (final Exception ex) {
            logger.error("HandleMessages", ex);
        } finally {
            readQueue.poll();
        }
    }

    private void sendMessages() {
        if (writeQueue.isEmpty()) {
            return;
        }

        final MsgReference msgRef = writeQueue.peek();

        try {
            switch (msgRef.sendType) {
            case UNICAST -> unicastMessage(msgRef);
            case BROADCAST -> broadcastMessage(msgRef);
            case BROADCAST_CHANNEL -> broadcastChannelMessage(msgRef);
            case BROADCAST_ROOM -> broadcastRoomMessage(msgRef);
            case BROADCAST_ROOM_EXCLUSIVE -> broadcastRoomMessageExclusive(msgRef);
            case BROADCAST_RED_TEAM -> broadcastRedTeamMessage(msgRef);
            case BROADCAST_BLUE_TEAM -> broadcastBlueTeamMessage(msgRef);
            }
        } catch (final Exception ex) {
            if (debugHandle) {
                logger.error("SendMessages", ex);
            } else {
                logger.error("SendMessages: {0}", ex.getMessage());
            }
        } finally {
            writeQueue.poll();
        }
    }

    /* =========================
       Netty send primitives (mirror C#)
       ========================= */

    private void unicastMessage(final MsgReference msgRef) {
        if (msgRef.client == null) {
            return;
        }
        sendToClient(msgRef.client, msgRef.msg.id(), msgRef.msg.msg());
    }

    private void broadcastMessage(final MsgReference msgRef) {
        for (final ClientReference c : clientList) {
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastChannelMessage(final MsgReference msgRef) {
        if (msgRef.channelRef == null) {
            return;
        }
        for (final ClientReference c : msgRef.channelRef.clientList) {
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastRoomMessage(final MsgReference msgRef) {
        if (msgRef.matchData == null) {
            return;
        }
        for (final ClientReference c : msgRef.matchData.clientList) {
            if (c.clientStatus.ordinal() < ClientStatus.ROOM.getId()) {
                continue;
            }
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastRedTeamMessage(final MsgReference msgRef) {
        for (final ClientReference c : clientList) {
            if ((c.clientStatus.ordinal() < ClientStatus.ROOM.getId()) || c.slot == null || !c.slot.isRed) {
                continue;
            }
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastBlueTeamMessage(final MsgReference msgRef) {
        for (final ClientReference c : clientList) {
            if ((c.clientStatus.ordinal() < ClientStatus.ROOM.getId()) || c.slot == null || c.slot.isRed) {
                continue;
            }
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastRoomMessageExclusive(final MsgReference msgRef) {
        if (msgRef.matchData == null || msgRef.client == null) {
            return;
        }
        for (final ClientReference c : msgRef.matchData.clientList) {
            if ((c == msgRef.client) || (c.clientStatus.ordinal() < ClientStatus.ROOM.getId())) {
                continue;
            }
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void sendToClient(final ClientReference client, final MessageId id, final MsgBody body) {
        if (client == null || client.socket == null || !client.socket.isActive()) {
            return;
        }

        final Msg4Send send = new Msg4Send(id.id(), 0xFFFFFFFFL, 0xFFFFFFFFL, body, sendKey);
        //logger.info("JAVA TX HEX (msgId={1}): {0}", io.netty.buffer.ByteBufUtil.hexDump(send.buffer()), id );
        client.socket.writeAndFlush(send.toByteBuf(client.socket.alloc()));

        if (debugSend) {
            logger.debug("Sent msgId={0} to {1}", id, client.GetIdentifier());
        }
    }

    /* =========================
       Shutdown / dead clients (as in C#)
       ========================= */

    public void shutdownInit() {
        channelManager.shutdown();
        channelManager = new ChannelManager();
        curSeq = 0;
        sendDisconnect(null, SendType.BROADCAST);
        waitForShutDown = true;
    }

    private void shutdownFinally() {
        synchronized (dataLock) {
            waitForShutDown = false;
            clearBuffers();
            clientList.clear();
            serverCreated = false;
        }
    }

    private void handleDeadClients(final float deltaTime) {
        for (final ClientReference client : clientList) {
            if (client.seq == -1) {
                client.toleranceTime += deltaTime;
                if (client.toleranceTime >= 3f) {
                    client.Disconnect(false);
                    break;
                }
            }
        }
    }

    /* =========================
       Ported SendConnected
       ========================= */

    public void sendConnected(final ClientReference client) {
        final MsgBody body = new MsgBody();
        say(new MsgReference(MessageId.EXT_OP_CONNECTED_ACK, body, client));

        if (debugSend) {
            logger.debug("SendConnected to {0}", client.GetIdentifier());
        }
    }

    /* =========================
       Disconnect
       ========================= */

    public void sendDisconnect(final ClientReference client, final SendType type) {
        final MsgBody body = new MsgBody();
        say(new MsgReference(MessageId.EXT_OP_DISCONNECT_REQ, body, client, type, null, null));
    }

    public void SendDeleteRoom(final MatchData matchData, final ChannelReference channel) {
        final MsgBody body = new MsgBody();

        body.write(matchData.room.no);

        say(new MsgReference(MessageId.CS_DEL_ROOM_ACK, body, null, SendType.BROADCAST_CHANNEL, channel, matchData));

        if (debugSend) {
            logger.debug("Broadcasted SendDelRoom for room no: {0}", matchData.room.no);
        }
    }
}
