package de.brickforceaurora.gameserver.handler;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.maps.RegMap;
import de.brickforceaurora.gameserver.maps.RegMapManager;
import de.brickforceaurora.gameserver.net.ChannelReference;
import de.brickforceaurora.gameserver.net.ClientReference;
import de.brickforceaurora.gameserver.net.MsgReference;
import de.brickforceaurora.gameserver.protocol.ExtensionOpcodes;
import de.brickforceaurora.gameserver.protocol.MessageId;
import de.brickforceaurora.gameserver.protocol.MsgBody;
import de.brickforceaurora.gameserver.room.Room;

import java.util.List;

public class LoginHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.CS_LOGIN_REQ.getId(), LoginHandlers::login);
        d.register(MessageId.CS_HEARTBEAT_REQ.getId(), LoginHandlers::heartbeat);
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
        SendChannels(server, client);
        SendCurChannel(server, client, channel.channel.id);
        SendInventoryRequest(server, client);
        SendLogin(server, client, channel.channel.id);
        SendPlayerInfo(server, client);
        sendAllDownloadedMaps(server, client);
        sendEmptyUserMap(server, client);
        sendAllUserMaps(server, client);
    }

    private static void heartbeat(GameServerLogic server, MsgReference msgRef) {
        int gmFunction = msgRef.msg.msg().readInt();
        // if (Time.time - msgRef.client.lastHeartBeatTime > 3f) msgRef.client.disconnect(true);
        // else msgRef.client.lastHeartBeatTime = Time.time;
    }

    public static void SendInventoryRequest(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(client.seq);

        server.say(new MsgReference(ExtensionOpcodes.OP_INVENTORY_REQ.getOpCode(), body, client));

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
        server.say(new MsgReference(148, body, client));

        server.logger().debug("SendPlayerInitInfo to: " + client.GetIdentifier());
    }

    public static void SendChannels(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(server.channelManager.getChannels().size());
        for (ChannelReference channelRef : server.channelManager.getChannels())
        {
            body.write(channelRef.channel.id);
            body.write(channelRef.channel.mode.getId());
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

        server.say(new MsgReference(141, body, client));

        server.logger().debug("SendChannels to: " + client.GetIdentifier());
    }

    public static void SendCurChannel(GameServerLogic server, ClientReference client)
    {
        SendCurChannel(server, client, 1);
    }
    public static void SendCurChannel(GameServerLogic server, ClientReference client, int curChannelId)
    {
        MsgBody body = new MsgBody();

        body.write(curChannelId);
        server.say(new MsgReference(147, body, client));

        server.logger().debug("SendCurChannel to: " + client.GetIdentifier());
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
        server.say(new MsgReference(2, body, client));

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
        server.say(new MsgReference(27, body, client));

        server.logger().debug("SendPlayerInfo to: " + client.GetIdentifier());
    }

    public static void sendAllUserMaps(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        int chunkSize = 200;
        List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        int chunkCount = (int) Math.ceil((double) maps.size() / (double) chunkSize);
        int processedCount = 0;

        for (int chunk = 0; chunk < chunkCount; chunk++)
        {
            int remaining = maps.size() - processedCount;
            if (remaining < chunkSize)
                chunkSize = remaining;

            body.write(-1); //page
            body.write(chunkSize); //count
            for (int i = 0; i < chunkSize; i++, processedCount++)
            {
                RegMap entry = maps.get(i);
                body.write(i); //slot
                body.write(entry.getAlias());
                body.write(-1); //brick count
                body.write(entry.getRegisteredDate().getYear());
                body.write((byte)entry.getRegisteredDate().getMonth().getValue());
                body.write((byte)entry.getRegisteredDate().getDayOfMonth());
                body.write((byte)entry.getRegisteredDate().getHour());
                body.write((byte)entry.getRegisteredDate().getMinute());
                body.write((byte)entry.getRegisteredDate().getSecond());
                body.write((byte)0); //premium
            }
            server.say(new MsgReference(430, body, client));
        }

        server.logger().debug("SendAllUserMaps to: " + client.GetIdentifier());
    }

    public static void sendAllDownloadedMaps(GameServerLogic server, ClientReference client)
    {
        int chunkSize = 100;
        List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        int chunkCount = (int) Math.ceil((double) maps.size() / (double) chunkSize);
        int processedCount = 0;

        for (int chunk = 0; chunk < chunkCount; chunk++)
        {
            int remaining = maps.size() - processedCount;
            if (remaining < chunkSize)
                chunkSize = remaining;

            MsgBody body = new MsgBody();

            body.write(-1); //page
            body.write(chunkSize); //count
            for (int i = 0; i < chunkSize; i++, processedCount++)
            {
                RegMap entry = maps.get(i);
                body.write(entry.getMap());
                body.write(entry.getDeveloper());
                body.write(entry.getAlias());
                body.writeUShort(entry.getModeMask());
                body.write((byte)(Room.clanMatch | Room.official));
                body.write(entry.tagMask);
                body.write(entry.getRegisteredDate().getYear());
                body.write((byte)entry.getRegisteredDate().getMonth().getValue());
                body.write((byte)entry.getRegisteredDate().getDayOfMonth());
                body.write((byte)entry.getRegisteredDate().getHour());
                body.write((byte)entry.getRegisteredDate().getMinute());
                body.write((byte)entry.getRegisteredDate().getSecond());
                body.write(entry.getDownloadFee());
                body.write(entry.getRelease());
                body.write(entry.getLatestRelease());
                body.write(entry.getLikes());
                body.write(entry.getDisLikes());
                body.write(entry.getDownloadCount());
            }

            server.say(new MsgReference(426, body, client));
        }

        server.logger().debug("SendAllDownloadedMaps to: " + client.GetIdentifier());
    }

    public static void sendEmptyUserMap(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(-1); //page
        body.write(1); //count
        body.write(0); //slot
        body.write("");
        body.write(-1); //brick count
        body.write(2000);
        body.write((byte)0);
        body.write((byte)0);
        body.write((byte)0);
        body.write((byte)0);
        body.write((byte)0);
        body.write((byte)0);

        server.say(new MsgReference(430, body, client));

        server.logger().debug("SendEmptyUserMap to: " + client.GetIdentifier());
    }
}
