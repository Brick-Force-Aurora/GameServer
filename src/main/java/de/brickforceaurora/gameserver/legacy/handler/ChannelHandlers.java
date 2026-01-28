package de.brickforceaurora.gameserver.legacy.handler;

import de.brickforceaurora.gameserver.legacy.channel.ChannelReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientReference;
import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;
import de.brickforceaurora.gameserver.legacy.protocol.SendType;

public class ChannelHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.CS_CHANNEL_PLAYER_LIST_REQ.id(), ChannelHandlers::userList);
    }

    private static void userList(GameServerLogic server, MsgReference msgRef)
    {
        server.logger().debug("HandleRequestUserList from: " + msgRef.client.GetIdentifier());

        SendUserList(server, msgRef.client);
    }

    public static void SendUserList(GameServerLogic server, ClientReference client)
    {
        SendUserList(server, client, SendType.UNICAST);
    }

    public static void SendUserList(GameServerLogic server, ClientReference client, SendType sendType)
    {
        MsgBody body = new MsgBody();

        body.write(client.channel.clientList.size());
        for (int i = 0; i < client.channel.clientList.size(); i++)
        {
            body.write(client.channel.clientList.get(i).seq);
            body.write(client.channel.clientList.get(i).name);
            body.write(client.channel.clientList.get(i).data.xp);
            body.write(client.channel.clientList.get(i).data.rank);

        }
        server.say(new MsgReference(MessageId.CS_SVC_ENTER_LIST_ACK, body, client, sendType));

        server.logger().debug("SendUserList to: " + client.GetIdentifier());
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

        server.say(new MsgReference(MessageId.CS_CHANNEL_ACK, body, client));

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
        server.say(new MsgReference(MessageId.CS_CUR_CHANNEL_ACK, body, client));

        server.logger().debug("SendCurChannel to: " + client.GetIdentifier());
    }
}
