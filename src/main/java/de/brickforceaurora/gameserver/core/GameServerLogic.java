package de.brickforceaurora.gameserver.core;

import de.brickforceaurora.gameserver.handler.*;
import de.brickforceaurora.gameserver.maps.RegMapManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SnowFrame;

import java.util.*;

import de.brickforceaurora.gameserver.match.MatchData;
import de.brickforceaurora.gameserver.net.*;
import de.brickforceaurora.gameserver.protocol.*;

@Deprecated
public final class GameServerLogic {

    private final Object dataLock = new Object();

    public final List<ClientReference> clientList = new ArrayList<>();
    private final Queue<MsgReference> readQueue  = new ArrayDeque<>();
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
    private float killLogTimer = 0f;

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

    public void tick(float deltaTime) {
        if (!serverCreated)
            return;

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

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ClientReference client = new ClientReference(ch);

                        ch.pipeline().addLast(new ReceiveHandler(client));

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

    public void enqueueIncoming(MsgReference msg) {
        synchronized (dataLock) {
            readQueue.add(msg);
        }
    }

    public void say(MsgReference msg) {
        synchronized (dataLock) {
            writeQueue.add(msg);
        }
    }

    public void sayInstant(MsgReference msg) {
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

    public boolean handleClientAccepted(ClientReference client) {
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
        if (readQueue.isEmpty())
            return;

        MsgReference msgRef = readQueue.peek();

        try {
            if (debugHandle && msgRef.client != null) {
                logger.debug("Processing message ID {0} from client {1}", msgRef.msg.id(), msgRef.client.GetIdentifier());
            }

            dispatcher.dispatch(this, msgRef);

        } catch (Exception ex) {
            logger.error("HandleMessages", ex);
        } finally {
            readQueue.poll();
        }
    }

    private void sendMessages() {
        if (writeQueue.isEmpty())
            return;

        MsgReference msgRef = writeQueue.peek();

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
        } catch (Exception ex) {
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

    private void unicastMessage(MsgReference msgRef) {
        if (msgRef.client == null) return;
        sendToClient(msgRef.client, msgRef.msg.id(), msgRef.msg.msg());
    }

    private void broadcastMessage(MsgReference msgRef) {
        for (ClientReference c : clientList) {
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastChannelMessage(MsgReference msgRef) {
        if (msgRef.channelRef == null) return;
        for (ClientReference c : msgRef.channelRef.clientList) {
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastRoomMessage(MsgReference msgRef) {
        if (msgRef.matchData == null) return;
        for (ClientReference c : msgRef.matchData.clientList) {
            if (c.clientStatus.ordinal() < ClientStatus.ROOM.getId())
                continue;
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastRedTeamMessage(MsgReference msgRef) {
        for (ClientReference c : clientList) {
            if (c.clientStatus.ordinal() < ClientStatus.ROOM.getId()) continue;
            if (c.slot == null || !c.slot.isRed) continue;
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastBlueTeamMessage(MsgReference msgRef) {
        for (ClientReference c : clientList) {
            if (c.clientStatus.ordinal() < ClientStatus.ROOM.getId()) continue;
            if (c.slot == null || c.slot.isRed) continue;
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void broadcastRoomMessageExclusive(MsgReference msgRef) {
        if (msgRef.matchData == null || msgRef.client == null) return;
        for (ClientReference c : msgRef.matchData.clientList) {
            if (c == msgRef.client) continue;
            if (c.clientStatus.ordinal() < ClientStatus.ROOM.getId()) continue;
            sendToClient(c, msgRef.msg.id(), msgRef.msg.msg());
        }
    }

    private void sendToClient(ClientReference client, int id, MsgBody body) {
        if (client == null || client.socket == null) return;
        if (!client.socket.isActive()) return;

        Msg4Send send = new Msg4Send(id, 0xFFFFFFFFL, 0xFFFFFFFFL, body, sendKey);
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

    private void handleDeadClients(float deltaTime) {
        for (ClientReference client : clientList) {
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

    public void sendConnected(ClientReference client) {
        MsgBody body = new MsgBody();
        say(new MsgReference(ExtensionOpcodes.OP_CONNECTED_ACK.getOpCode(), body, client));

        if (debugSend) {
            logger.debug("SendConnected to {0}", client.GetIdentifier());
        }
    }

    /* =========================
       Disconnect
       ========================= */

    public void sendDisconnect(ClientReference client, SendType type) {
        MsgBody body = new MsgBody();
        say(new MsgReference(ExtensionOpcodes.OP_DISCONNECT_REQ.getOpCode(), body, client, type, null, null));
    }

    public void SendDeleteRoom(MatchData matchData, ChannelReference channel)
    {
        MsgBody body = new MsgBody();

        body.write(matchData.room.no);

        say(new MsgReference(6, body, null, SendType.BROADCAST_CHANNEL, channel, matchData));

        if (debugSend) {
            logger.debug("Broadcasted SendDelRoom for room no: {0}", matchData.room.no);
        }
    }
}
