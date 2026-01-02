package de.brickforceaurora.gameserver.handler;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.net.ClientReference;
import de.brickforceaurora.gameserver.net.MsgReference;
import de.brickforceaurora.gameserver.net.SendType;
import de.brickforceaurora.gameserver.protocol.MessageId;
import de.brickforceaurora.gameserver.protocol.MsgBody;

public class ChannelHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.CS_CHANNEL_PLAYER_LIST_REQ.getId(), ChannelHandlers::userList);
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
        server.say(new MsgReference(MessageId.CS_SVC_ENTER_LIST_ACK.getId(), body, client, sendType));

        server.logger().debug("SendUserList to: " + client.GetIdentifier());
    }
}
