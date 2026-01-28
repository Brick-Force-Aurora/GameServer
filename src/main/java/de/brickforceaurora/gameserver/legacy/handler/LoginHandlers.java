package de.brickforceaurora.gameserver.legacy.handler;

import de.brickforceaurora.gameserver.legacy.channel.ChannelReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientReference;
import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;

public class LoginHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.CS_LOGIN_REQ.id(), LoginHandlers::login);
        d.register(MessageId.CS_HEARTBEAT_REQ.id(), LoginHandlers::heartbeat);
    }

    private static void login(GameServerLogic server, MsgReference msgRef) {

        MsgBody msg = msgRef.msg.msg();

        String id = msg.readString();
        String pswd = msg.readString();
        int major = msg.readInt();
        int minor = msg.readInt();
        String privateIpAddress = msg.readString();
        String macAddress = msg.readString();

        ClientReference client = msgRef.client;

        client.name = id;
        client.seq = server.curSeq;
        client.port = 6000 + client.seq;
        server.curSeq++;

        ChannelReference channel = server.channelManager.getDefaultChannel();
        channel.addClient(client);

        SendPlayerInitInfo(server, client);
        ChannelHandlers.SendChannels(server, client);
        ChannelHandlers.SendCurChannel(server, client, channel.channel.id);
        SendInventoryRequest(server, client);
        SendLogin(server, client, channel.channel.id);
        SendPlayerInfo(server, client);
        MapHandlers.sendAllDownloadedMaps(server, client);
        MapHandlers.sendEmptyUserMap(server, client);
        MapHandlers.sendAllUserMaps(server, client);
    }

    private static void heartbeat(GameServerLogic server, MsgReference msgRef) {

        int gmFunction = msgRef.msg.msg().readInt();

        ClientReference client = msgRef.client;

        long now = System.nanoTime(); // monotonic, safe for deltas

        if ((now - client.lastHeartBeatTime) > 3_000_000_000L) {
            client.Disconnect(true);
        } else {
            client.lastHeartBeatTime = now;
        }
    }


    public static void SendInventoryRequest(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(client.seq);

        server.say(new MsgReference(MessageId.EXT_OP_INVENTORY_REQ, body, client));

        server.logger().debug("SendInventoryRequest to: " + client.GetIdentifier());
    }

    public static void SendPlayerInitInfo(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(client.data.xp);
        body.write(client.data.tutorialed);
        body.write(client.data.countryFilter);
        body.write(client.data.tos);
        body.write(client.data.extraSlots);
        body.write(client.data.rank);
        body.write(client.data.firstLoginFp);
        server.say(new MsgReference(MessageId.CS_PLAYER_INIT_INFO_ACK, body, client));

        server.logger().debug("SendPlayerInitInfo to: " + client.GetIdentifier());
    }

    public static void SendLogin(GameServerLogic server, ClientReference client)
    {
        SendLogin(server, client, 1);
    }

    public static void SendLogin(GameServerLogic server, ClientReference client, int loginChannelId)
    {
        MsgBody body = new MsgBody();

        body.write(client.seq);
        body.write(loginChannelId);
        server.say(new MsgReference(MessageId.CS_LOGIN_ACK, body, client));

        server.logger().debug("SendLogin to: " + client.GetIdentifier());
    }

    public static void SendPlayerInfo(GameServerLogic server, ClientReference client)
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
        server.say(new MsgReference(MessageId.CS_PLAYER_INFO_ACK, body, client));

        server.logger().debug("SendPlayerInfo to: " + client.GetIdentifier());
    }
}
