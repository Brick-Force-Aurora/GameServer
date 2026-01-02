package de.brickforceaurora.gameserver.handler;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.match.MatchData;
import de.brickforceaurora.gameserver.net.ClientReference;
import de.brickforceaurora.gameserver.net.ClientStatus;
import de.brickforceaurora.gameserver.net.MsgReference;
import de.brickforceaurora.gameserver.net.SendType;
import de.brickforceaurora.gameserver.protocol.ExtensionOpcodes;
import de.brickforceaurora.gameserver.protocol.MessageId;
import de.brickforceaurora.gameserver.protocol.MsgBody;
import de.brickforceaurora.gameserver.room.RoomType;
import de.brickforceaurora.gameserver.handler.ChannelHandlers;

public class RoomHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.CS_ROOM_LIST_REQ.getId(), RoomHandlers::roomList);
        d.register(MessageId.CS_CREATE_ROOM_REQ.getId(), RoomHandlers::createRoom);
        d.register(MessageId.CS_ROOM_CONFIG_REQ.getId(), RoomHandlers::roomConfig);
        d.register(MessageId.CS_ROAMIN_REQ.getId(), RoomHandlers::roamIn);
    }

    private static void roomList(GameServerLogic server, MsgReference msgRef)
    {
        server.logger().debug("HandleRoomListRequest from: " + msgRef.client.GetIdentifier());

        SendRoomList(server, msgRef.client);
    }

    public static void SendRoomList(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        if (client.channel == null)
            body.write(0); //count
        else
        {
            body.write(client.channel.matches.size()); //count
            for (int i = 0; i < client.channel.matches.size(); i++)
            {
                MatchData matchData = client.channel.matches.get(i);
                body.write(matchData.room.no);
                body.write(matchData.room.type.getId());
                body.write(matchData.room.title);
                body.write(matchData.room.locked);
                body.write(matchData.room.status.getId());
                body.write(matchData.room.curPlayer);
                body.write(matchData.room.maxPlayer);
                body.write(matchData.room.map);
                body.write(matchData.room.curMapAlias);
                body.write(matchData.room.goal);
                body.write(matchData.room.timelimit);
                body.write(matchData.room.weaponOption);
                body.write(matchData.room.ping);
                body.write(matchData.room.score1);
                body.write(matchData.room.score2);
                body.write(matchData.room.CountryFilter);
                body.write(matchData.room.isBreakInto);
                body.write(matchData.room.isDropItem);
                body.write(matchData.room.isWanted);
                body.write(matchData.room.squad);
                body.write(matchData.room.squadCounter);
            }
        }

        server.say(new MsgReference(468, body, client));

        server.logger().debug("SendRoomList to: " + client.GetIdentifier());
    }

    private static void createRoom(GameServerLogic server, MsgReference msgRef)
    {
        int type = msgRef.msg.msg().readInt();
        String title = msgRef.msg.msg().readString();
        boolean isLocked = msgRef.msg.msg().readBool();
        String pwsd = msgRef.msg.msg().readString();
        int maxPlayer = msgRef.msg.msg().readInt();
        int param1 = msgRef.msg.msg().readInt();    //Play: goal			Build: isLoad
        int param2 = msgRef.msg.msg().readInt();    //Play: timeLimit		Build: slot
        int param3 = msgRef.msg.msg().readInt();    //Play: weaponOption	Build: brickCount:landscapeIndex
        int param4 = msgRef.msg.msg().readInt();    //Play: map				Build: map:skyboxIndex
        int param5 = msgRef.msg.msg().readInt();    //Play: breakInto		Build: premium
        int param6 = msgRef.msg.msg().readInt();    //Play: isBalance		Build: N/A
        int param7 = msgRef.msg.msg().readInt();    //Play: isWanted		Build: N/A
        int param8 = msgRef.msg.msg().readInt();    //Play: isDrop			Build: N/A
        String alias = msgRef.msg.msg().readString();

        MatchData matchData = msgRef.client.channel.addNewMatch();

        RoomType roomType = RoomType.fromValue(type);
        matchData.room.type = roomType;
        matchData.room.title = title;
        matchData.room.locked = isLocked;
        matchData.room.maxPlayer = maxPlayer;
        matchData.room.curMapAlias = alias;
        matchData.masterSeq = msgRef.client.seq;
        matchData.lockSlotsByMaxPlayers(matchData.room.maxPlayer, roomType);
        matchData.roomCreated = true;

        if (roomType == RoomType.MAP_EDITOR)
        {
            if (param1 == 1)
            {
                //matchData.CacheMap(regMaps.Find(x => x.Value.Map == param2).Value, new UserMapInfo(param2, (sbyte)param5));
                //MAP EDITOR CURENTYL DISABLED
                //matchData.CacheMap(regMaps.Find(x => x.Value.Map == param2).Value, UserMapInfoManager.Instance.Get(param2));
            }
            else
            {
                server.logger().debug("CreateRoomRequest: Generate Map");
                matchData.cacheMapGenerate(param3, param4, alias);
            }
        }
        else
        {
            matchData.room.goal = param1;
            matchData.room.timelimit = param2;
            matchData.room.weaponOption = param3;
            matchData.room.map = param4;
            matchData.room.isBreakInto = param5 != 0;
            matchData.room.isWanted = param7 != 0;
            matchData.room.isDropItem = param8 != 0;
            matchData.isBalance = param6 != 0;
        }

        if (roomType == RoomType.BUNGEE)
        {
            //matchData.CacheMap(regMaps.Find(x => x.Value.Map == param4).Value, new UserMapInfo(0, 0));
        }

        if (roomType == RoomType.BND)
        {
            // Unpack the timer configuration for Build and Destroy phases
            int buildTime, destroyTime, repeat;
            //BND.UnpackTimerOption(param2, out buildTime, out destroyTime, out repeat);

            //matchData.buildPhaseTime = buildTime;
            //matchData.battlePhaseTime = destroyTime;
            //matchData.repeat = repeat;

            // Initialize BND-specific fields
        /*matchData.currentPhase = MatchData.BnDPhase.Build;
        matchData.currentRound = 1;
        matchData.remainTime = buildTime; // Start with Build phase time*/
        }

        server.logger().debug("HandleCreateRoom from: " + msgRef.client.GetIdentifier());

        matchData.AddClient(msgRef.client);

        SendRendezvousInfo(server, msgRef.client);

        SendMaster(server, msgRef.client, matchData);
        SendSlotLocks(server, msgRef.client);
        SendRoomConfig(server, msgRef.client, matchData);
        SendAddRoom(server, msgRef.client, matchData);
        SendCreateRoom(server, msgRef.client);

        SendEnter(server, msgRef.client);

        sendSlotData(server, matchData);

        if (roomType == RoomType.MAP_EDITOR)
            SendCopyright(server, msgRef.client);
    }

    public static void SendRendezvousInfo(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        body.write(0); //unused
        body.write(client.ip);
        body.write(client.port);

        server.say(new MsgReference(320, body, client));

        server.logger().debug("SendRendezvousInfo to: " + client.GetIdentifier());
    }

    public static void SendMaster(GameServerLogic server, ClientReference client, MatchData matchData)
    {
        MsgBody body = new MsgBody();

        body.write(matchData.masterSeq);

        if (client == null)
        {
            server.say(new MsgReference(31, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

            server.logger().debug("Broadcasted SendMaster for room no: " + matchData.room.no);
        }

        else
        {
            server.say(new MsgReference(31, body, client));

            server.logger().debug("SendMaster to: " + client.GetIdentifier());
        }
    }

    public static void SendSlotLocks(GameServerLogic server, ClientReference client)
    {
        MatchData matchData = client.matchData;
        for (byte i = 0; i < matchData.slots.size(); i++)
        {
            SendSlotLock(server, client, matchData, i);
        }

        server.logger().debug("SendSlots to: " + client.GetIdentifier());
    }

    public static void SendSlotLock(GameServerLogic server, ClientReference client, MatchData matchData, byte index)
    {
        SendSlotLock(server, client, matchData, index, SendType.UNICAST);
    }

    public static void SendSlotLock(GameServerLogic server, ClientReference client, MatchData matchData, byte index, SendType sendType)
    {
        MsgBody body = new MsgBody();

        body.write(index);
        body.write(matchData.slots.get(index).isLocked ? (byte) 1 : (byte) 0);
        server.say(new MsgReference(86, body, client, sendType, matchData.channel, matchData));

        if (sendType == SendType.UNICAST)
            server.logger().debug("SendSlotLock to: " + client.GetIdentifier());
        else
            server.logger().debug("Broadcasted SendSlotLock for room no " + matchData.room.no);
    }

    public static void SendEnter(GameServerLogic server, ClientReference client)
    {
        MatchData matchData = client.matchData;

        MsgBody body = new MsgBody();

        body.write(client.slot.slotIndex);
        body.write(client.seq);
        body.write(client.name);
        body.write(client.ip);
        body.write(client.port); //port
        body.write(client.ip);
        body.write(client.port); //remotePort
        body.write(client.inventory.equipmentString.length);
        for (int i = 0; i < client.inventory.equipmentString.length; i++)
        {
            body.write(client.inventory.equipmentString[i]);
        }
        body.write(client.status.ordinal());
        body.write(client.data.xp);
        body.write(client.data.clanSeq);
        body.write(client.data.clanName);
        body.write(client.data.clanMark);
        body.write(client.data.rank);
        body.write((byte)1); //playerflag
        body.write(client.inventory.weaponChgString.length);
        for (int i = 0; i < client.inventory.weaponChgString.length; i++)
        {
            body.write(client.inventory.weaponChgString[i]);
        }
        body.write(0); //drpItem count

        server.say(new MsgReference(10, body, client, SendType.BROADCAST_ROOM_EXCLUSIVE, matchData.channel, matchData));
        server.logger().debug("Broadcasted SendEnter for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    public static void SendCopyright(GameServerLogic server, ClientReference client)
    {
        MsgBody body = new MsgBody();

        MatchData matchData = client.matchData;

        body.write(matchData.masterSeq);
        body.write(matchData.cachedUMI.slot);

        server.say(new MsgReference(53, body, client));
        server.logger().debug("SendCopyRight to: " + client.GetIdentifier());
    }

    public static void SendAddRoom(GameServerLogic server, ClientReference client, MatchData matchData)
    {
        MsgBody body = new MsgBody();

        body.write(matchData.room.no);
        body.write(matchData.room.type.getId());
        body.write(matchData.room.title);
        body.write(matchData.room.locked);
        body.write(matchData.room.status.getId());
        body.write(matchData.room.curPlayer);
        body.write(matchData.room.maxPlayer);
        body.write(matchData.room.map);
        body.write(matchData.room.curMapAlias);
        body.write(matchData.room.goal);
        body.write(matchData.room.timelimit);
        body.write(matchData.room.weaponOption);
        body.write(matchData.room.ping);
        body.write(matchData.room.score1);
        body.write(matchData.room.score2);
        body.write(matchData.room.CountryFilter);
        body.write(matchData.room.isBreakInto);
        body.write(matchData.room.isDropItem);
        body.write(matchData.room.isWanted);
        body.write(matchData.room.squad);
        body.write(matchData.room.squadCounter);

        if (client == null)
        {
            server.say(new MsgReference(5, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));
            server.logger().debug("Broadcasted SendAddRoom for room no: " + matchData.room.no);
        }
        else
        {
            server.say(new MsgReference(5, body, client));
            server.logger().debug("SendAddRoom to: " + client.GetIdentifier());
        }
    }

    public static void SendCreateRoom(GameServerLogic server, ClientReference client)
    {
        SendCreateRoom(server, client, true);
    }

    public static void SendCreateRoom(GameServerLogic server, ClientReference client, boolean success)
    {
        MatchData matchData = client.matchData;

        MsgBody body = new MsgBody();

        body.write(matchData.room.type.getId());
        body.write(success ? matchData.room.no : -1);
        body.write(matchData.room.title);

        server.say(new MsgReference(8, body, client));

        server.logger().debug("SendCreateRoom to: " + client.GetIdentifier());
    }

    private static void roomConfig(GameServerLogic server, MsgReference msgRef)
    {
        MatchData matchData = msgRef.matchData;

        int killCount = msgRef.msg.msg().readInt();
        int timeLimit = msgRef.msg.msg().readInt();
        int weaponOption = msgRef.msg.msg().readInt();
        int nWhere = msgRef.msg.msg().readInt();
        int breakInto = msgRef.msg.msg().readInt();
        int teamBalance = msgRef.msg.msg().readInt();
        int useBuildGun = msgRef.msg.msg().readInt();
        int itemPickup = msgRef.msg.msg().readInt();
        String whereAlias = msgRef.msg.msg().readString();
        String pswd = msgRef.msg.msg().readString();
        int type = msgRef.msg.msg().readInt();

        matchData.room.goal = killCount;
        matchData.room.timelimit = timeLimit;
        matchData.room.weaponOption = weaponOption;
        matchData.room.map = nWhere;
        matchData.room.isBreakInto = breakInto != 0;
        matchData.isBalance = teamBalance != 0;
        matchData.room.isDropItem = itemPickup != 0;
        matchData.room.curMapAlias = whereAlias;
        matchData.room.type = RoomType.fromValue(type);

        server.logger().debug("HandleRoomConfig from: " + msgRef.client.GetIdentifier());

        SendRoomConfig(server,null, matchData);
    }

    public static void SendRoomConfig(GameServerLogic server, ClientReference client, MatchData matchData)
    {
        MsgBody body = new MsgBody();

        body.write(matchData.room.map);
        body.write(matchData.room.curMapAlias);
        if (matchData.room.type == RoomType.MISSION)
        {
            body.write(matchData.room.goal); // core HP
        } else
        {
            body.write(matchData.room.weaponOption);
        }
        body.write(matchData.room.timelimit);
        body.write(matchData.room.goal);
        body.write(matchData.room.isBreakInto);
        body.write(matchData.isBalance);
        body.write(false); //useBuildGun
        body.write(""); //password
        body.write((byte)0); //commented
        body.write(matchData.room.type.getId());
        body.write(matchData.room.isDropItem);
        body.write(matchData.room.isWanted);

        if (client == null)
        {
            server.say(new MsgReference(92, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));
            server.logger().debug("Broadcasted SendRoomConfig for room no: " + matchData.room.no);
        }
        else
        {
            server.say(new MsgReference(92, body, client));
            server.logger().debug("SendRoomConfig to: " + client.GetIdentifier());
        }
    }

    private static void roamIn(GameServerLogic server, MsgReference msgRef)
    {
        int seq = msgRef.msg.msg().readInt();
        int userType = msgRef.msg.msg().readInt();
        boolean isWebPlayer = msgRef.msg.msg().readBool();
        int language = msgRef.msg.msg().readInt();
        String hashCode = msgRef.msg.msg().readString();

        server.logger().debug("HandleRoamin from: " + msgRef.client.GetIdentifier());

        ChannelHandlers.SendUserList(server, msgRef.client);
        SendRoamin(server, msgRef.client, msgRef.client.channel.channel.id);

        msgRef.client.clientStatus = ClientStatus.LOBBY;
    }

    public static void SendRoamin(GameServerLogic server, ClientReference client, int dest)
    {
        SendRoamin(server, client, dest, SendType.UNICAST);
    }

    public static void SendRoamin(GameServerLogic server, ClientReference client, int dest, SendType sendType)
    {
        MsgBody body = new MsgBody();

        body.write(dest);
        server.say(new MsgReference(MessageId.CS_ROAMIN_ACK.getId(), body, client, sendType));

        server.logger().debug("SendRoamin to: " + client.GetIdentifier());
    }

    public static void sendSlotData(GameServerLogic server, MatchData matchData) {
        MsgBody body = new MsgBody();

        if (matchData == null || matchData.channel == null) {
            server.logger().debug("[SendSlotData] Client does not exist anymore");
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
        server.say(new MsgReference(
                ExtensionOpcodes.OP_SLOT_DATA_ACK.getOpCode(),
                body,
                null,
                SendType.BROADCAST_ROOM,
                matchData.channel,
                matchData
        ));

        server.say(new MsgReference(
                ExtensionOpcodes.OP_SLOT_DATA_ACK.getOpCode(),
                body,
                null,
                SendType.BROADCAST_ROOM,
                matchData.channel,
                matchData
        ));

        server.logger().debug("Broadcasted SendSlotData for room no: {0}", matchData.room.no);
    }
}
