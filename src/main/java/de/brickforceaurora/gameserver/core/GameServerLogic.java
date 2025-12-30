package de.brickforceaurora.gameserver.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import me.lauriichan.laylib.logger.ISimpleLogger;

import java.util.*;
import java.util.logging.Logger;

import de.brickforceaurora.gameserver.match.MatchData;
import de.brickforceaurora.gameserver.net.*;
import de.brickforceaurora.gameserver.protocol.*;

@Deprecated
public final class GameServerLogic {

    private final Object dataLock = new Object();

    public final List<ClientReference> clientList = new ArrayList<>();
    private final Queue<MsgReference> readQueue  = new ArrayDeque<>();
    private final Queue<MsgReference> writeQueue = new ArrayDeque<>();

    private final Map<Integer, MessagePassingQueue.Consumer<MsgReference>> handlers = new HashMap<>();

    public boolean debugHandle = true;
    public boolean debugSend = true;
    public boolean debugPing = false;

    private int curSeq = 0;

    public boolean serverCreated = false;
    public boolean isSteam = false;

    public byte recvKey = (byte) 0xFF;
    public byte sendKey = (byte) 0xFF;

    private boolean waitForShutDown = false;
    private float killLogTimer = 0f;

    public ChannelManager channelManager = new ChannelManager();
    
    private final ISimpleLogger logger;

    /* ===== Startup ===== */

    public GameServerLogic(final ISimpleLogger logger) {
        this.logger = logger;
        registerHandlers();
        serverCreated = false;
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
        // Example
        handlers.put(MessageId.CS_LOGIN_REQ.getId(), this::handleLoginRequest);
        handlers.put(MessageId.CS_HEARTBEAT_REQ.getId(), this::handleHeartbeat);

        // Add all others same way
    }

    private void handleMessages() {
        if (readQueue.isEmpty())
            return;

        MsgReference msgRef = readQueue.peek();

        try {
            if (debugHandle && msgRef.client != null) {
                logger.debug("Processing message ID {0} from client {1}", msgRef.msg.id(), msgRef.client.GetIdentifier());
            }

            MessagePassingQueue.Consumer<MsgReference> handler = handlers.get(msgRef.msg.id());
            if (handler != null) {
                handler.accept(msgRef);
            } else {
                logger.warning("No handler for message ID {0}", msgRef.msg.id());
            }

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
       Port examples for handlers you pasted
       ========================= */

    private void handleHeartbeat(MsgReference msgRef) {
        int gmFunction = msgRef.msg.msg().readInt();
        // if (Time.time - msgRef.client.lastHeartBeatTime > 3f) msgRef.client.disconnect(true);
        // else msgRef.client.lastHeartBeatTime = Time.time;
    }

    private void handleLoginRequest(MsgReference msgRef) {

        MsgBody msg = msgRef.msg.msg();

        String id = msg.readString();
        String pswd = msg.readString();
        int major = msg.readInt();
        int minor = msg.readInt();
        String privateIpAddress = msg.readString();
        String macAddress = msg.readString();

        ClientReference client = msgRef.client;

        client.name = id;
        client.seq = curSeq;
        client.port = 6000 + client.seq;
        curSeq++;

        ChannelReference channel = channelManager.getDefaultChannel();
        channel.addClient(client);

        SendPlayerInitInfo(client);
        SendChannels(client);
        SendCurChannel(client, channel.channel.id);
        //sendInventoryRequest(client);
        SendLogin(client, channel.channel.id);
        SendPlayerInfo(client);
        //sendAllDownloadedMaps(client);
        //sendEmptyUserMap(client);
        //sendAllUserMaps(client);
    }

    public void SendPlayerInitInfo(ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(client.data.xp);
        body.write(client.data.tutorialed);
        body.write(client.data.countryFilter);
        body.write(client.data.tos);
        body.write(client.data.extraSlots);
        body.write(client.data.rank);
        body.write(client.data.firstLoginFp);
        say(new MsgReference(148, body, client));

        logger.debug("SendPlayerInitInfo to: " + client.GetIdentifier());
    }

    public void SendChannels(ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(channelManager.getChannels().size());
        for (ChannelReference channelRef : channelManager.getChannels())
        {
            body.write(channelRef.channel.id);
            body.write(channelRef.channel.mode.ordinal());
            body.write(channelRef.channel.name);
            body.write(channelRef.channel.ip);
            body.write(channelRef.channel.port);
            body.write(channelRef.channel.userCount);
            body.write(channelRef.channel.maxUserCount);
            body.write(channelRef.channel.country);
            body.write((byte)channelRef.channel.minLvRank);
            body.write((byte)channelRef.channel.maxLvRank);
            body.writeUShort(channelRef.channel.xpBonus);
            body.writeUShort(channelRef.channel.fpBonus);
            body.write(channelRef.channel.limitStarRate);
        }

        say(new MsgReference(141, body, client));

        if (debugSend)
            logger.debug("SendChannels to: " + client.GetIdentifier());
    }

    public void SendCurChannel(ClientReference client)
    {
        SendCurChannel(client, 1);
    }
    public void SendCurChannel(ClientReference client, int curChannelId)
    {
        MsgBody body = new MsgBody();

        body.write(curChannelId);
        say(new MsgReference(147, body, client));

        if (debugSend)
            logger.debug("SendCurChannel to: " + client.GetIdentifier());
    }

    public void SendLogin(ClientReference client)
    {
        SendLogin(client, 1);
    }

    public void SendLogin(ClientReference client, int loginChannelId)
    {
        MsgBody body = new MsgBody();

        body.write(client.seq);
        body.write(loginChannelId);
        say(new MsgReference(2, body, client));

        if (debugSend)
            logger.debug("SendLogin to: " + client.GetIdentifier());
    }

    public void SendPlayerInfo(ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(client.name);
        body.write(client.data.xp);
        body.write(client.data.forcePoints);
        body.write(client.data.brickPoints);
        body.write(client.data.tokens);
        body.write(0);
        body.write(client.data.coins);
        body.write(client.data.starDust);
        body.write(6);
        body.write(5);
        body.write(client.data.gm);
        body.write(client.data.clanSeq);
        body.write(client.data.clanName);
        body.write(client.data.clanLv);
        body.write(client.data.rank);
        body.write(client.data.heavy);
        body.write(client.data.assault);
        body.write(client.data.sniper);
        body.write(client.data.subMachine);
        body.write(client.data.handGun);
        body.write(client.data.melee);
        body.write(client.data.special);
        say(new MsgReference(27, body, client));

        if (debugSend)
            logger.debug("SendPlayerInfo to: " + client.GetIdentifier());
    }


    /* =========================
       Disconnect (stub call target, no TODO)
       ========================= */

    public void sendDisconnect(ClientReference client, SendType type) {
        MsgBody body = new MsgBody();
        say(new MsgReference(ExtensionOpcodes.OP_DISCONNECT_REQ.getOpCode(), body, client, type, null, null));
    }

    public void sendLeave(ClientReference client) {
        MatchData matchData = client.matchData;

        if (matchData != null && matchData.clientList.contains(client)) {
            matchData.RemoveClient(client);
        }

        if (matchData != null && matchData.room.curPlayer <= 0) {
            SendDeleteRoom(matchData, matchData.channel);
            client.channel.removeMatch(matchData);
            return;
        }

        MsgBody body = new MsgBody();
        body.write(client.seq);

        if (matchData == null || matchData.channel == null) {
            if (debugSend) {
                logger.debug("[SendLeave] Client left but was not in a room: {0}", client.GetIdentifier());
            }
            return;
        }

        say(new MsgReference(
                11, // CS_LEAVE_ACK
                body,
                client,
                SendType.BROADCAST_ROOM,
                matchData.channel,
                matchData
        ));

        if (debugSend) {
            logger.debug("Broadcasted SendLeave for client {0} for room no {1}", client.GetIdentifier(), matchData.room.no);
        }
    }

    public void sendSlotData(MatchData matchData) {
        MsgBody body = new MsgBody();

        if (matchData == null || matchData.channel == null) {
            if (debugSend) {
                logger.debug("[SendSlotData] Client does not exist anymore");
            }
            return;
        }

        body.write(matchData.clientList.size());

        for (ClientReference client : matchData.clientList) {
            body.write(client.slot.slotIndex);
            body.write(client.seq);
            body.write(client.name);
            body.write(client.ip);
            body.write(client.port); // port
            body.write(client.ip);
            body.write(client.port); // remotePort

            //body.write(client.inventory.equipmentString.length);
            body.write(0);
            /*for (int v : client.inventory.equipmentString) {
                body.write(v);
            }*/

            body.write(client.status.ordinal());
            body.write(client.data.xp);
            body.write(client.data.clanSeq);
            body.write(client.data.clanName);
            body.write(client.data.clanMark);
            body.write(client.data.rank);
            body.write((byte) 1); // playerflag

            //body.write(client.inventory.weaponChgString.length);
            body.write(0);
            /*for (int v : client.inventory.weaponChgString) {
                body.write(v);
            }*/

            body.write(0); // drop item count
        }

        // yes, twice — exactly like original
        say(new MsgReference(
                ExtensionOpcodes.OP_SLOT_DATA_ACK.getOpCode(),
                body,
                null,
                SendType.BROADCAST_ROOM,
                matchData.channel,
                matchData
        ));

        say(new MsgReference(
                ExtensionOpcodes.OP_SLOT_DATA_ACK.getOpCode(),
                body,
                null,
                SendType.BROADCAST_ROOM,
                matchData.channel,
                matchData
        ));

        if (debugSend) {
            logger.debug("Broadcasted SendSlotData for room no: {0}", matchData.room.no);
        }
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
