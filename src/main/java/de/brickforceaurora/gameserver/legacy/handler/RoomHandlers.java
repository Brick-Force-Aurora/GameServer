package de.brickforceaurora.gameserver.legacy.handler;

import de.brickforceaurora.gameserver.legacy.channel.BrickManStatus;
import de.brickforceaurora.gameserver.legacy.channel.ChannelReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientStatus;
import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.match.MatchData;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;
import de.brickforceaurora.gameserver.legacy.protocol.SendType;
import de.brickforceaurora.gameserver.legacy.room.RoomStatus;
import de.brickforceaurora.gameserver.legacy.room.RoomType;

public class RoomHandlers {

    public static void register(final MessageDispatcher d) {
        d.register(MessageId.CS_ROOM_LIST_REQ.id(), RoomHandlers::roomList);
        d.register(MessageId.CS_CREATE_ROOM_REQ.id(), RoomHandlers::createRoom);
        d.register(MessageId.CS_ROOM_CONFIG_REQ.id(), RoomHandlers::roomConfig);
        d.register(MessageId.CS_ROAMIN_REQ.id(), RoomHandlers::roamIn);
        d.register(MessageId.CS_LEAVE_REQ.id(), RoomHandlers::leave);
        d.register(MessageId.CS_JOIN_REQ.id(), RoomHandlers::join);
        d.register(MessageId.CS_RESUME_ROOM_REQ.id(), RoomHandlers::resume);
        d.register(MessageId.CS_TEAM_CHANGE_REQ.id(), RoomHandlers::teamChange);
        d.register(MessageId.CS_KICK_REQ.id(), RoomHandlers::kick);
        d.register(MessageId.CS_SLOT_LOCK_REQ.id(), RoomHandlers::slotLock);
        d.register(MessageId.CS_START_REQ.id(), RoomHandlers::start);
        d.register(MessageId.CS_SET_STATUS_REQ.id(), RoomHandlers::setStatus);
        d.register(MessageId.CS_DELEGATE_MASTER_REQ.id(), RoomHandlers::delegateMaster);
    }

    private static void roomList(final GameServerLogic server, final MsgReference msgRef) {
        server.logger().debug("HandleRoomListRequest from: " + msgRef.client.GetIdentifier());

        SendRoomList(server, msgRef.client);
    }

    public static void SendRoomList(final GameServerLogic server, final ClientReference client) {
        final MsgBody body = new MsgBody();

        if (client.channel == null) {
            body.write(0); //count
        } else {
            body.write(client.channel.matches.size()); //count
            for (int i = 0; i < client.channel.matches.size(); i++) {
                final MatchData matchData = client.channel.matches.get(i);
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

        server.say(new MsgReference(MessageId.CS_ROOM_LIST_ACK, body, client));

        server.logger().debug("SendRoomList to: " + client.GetIdentifier());
    }

    private static void createRoom(final GameServerLogic server, final MsgReference msgRef) {
        final int type = msgRef.msg.msg().readInt();
        final String title = msgRef.msg.msg().readString();
        final boolean isLocked = msgRef.msg.msg().readBool();
        final String pwsd = msgRef.msg.msg().readString();
        final int maxPlayer = msgRef.msg.msg().readInt();
        final int param1 = msgRef.msg.msg().readInt();    //Play: goal			Build: isLoad
        final int param2 = msgRef.msg.msg().readInt();    //Play: timeLimit		Build: slot
        final int param3 = msgRef.msg.msg().readInt();    //Play: weaponOption	Build: brickCount:landscapeIndex
        final int param4 = msgRef.msg.msg().readInt();    //Play: map				Build: map:skyboxIndex
        final int param5 = msgRef.msg.msg().readInt();    //Play: breakInto		Build: premium
        final int param6 = msgRef.msg.msg().readInt();    //Play: isBalance		Build: N/A
        final int param7 = msgRef.msg.msg().readInt();    //Play: isWanted		Build: N/A
        final int param8 = msgRef.msg.msg().readInt();    //Play: isDrop			Build: N/A
        final String alias = msgRef.msg.msg().readString();

        final MatchData matchData = msgRef.client.channel.addNewMatch();

        final RoomType roomType = RoomType.fromValue(type);
        matchData.room.type = roomType;
        matchData.room.title = title;
        matchData.room.locked = isLocked;
        matchData.room.maxPlayer = maxPlayer;
        matchData.room.curMapAlias = alias;
        matchData.masterSeq = msgRef.client.seq;
        matchData.lockSlotsByMaxPlayers(matchData.room.maxPlayer, roomType);
        matchData.roomCreated = true;

        if (roomType == RoomType.MAP_EDITOR) {
            if (param1 == 1) {
                //matchData.CacheMap(regMaps.Find(x => x.Value.Map == param2).Value, new UserMapInfo(param2, (sbyte)param5));
                //MAP EDITOR CURENTYL DISABLED
                //matchData.CacheMap(regMaps.Find(x => x.Value.Map == param2).Value, UserMapInfoManager.Instance.Get(param2));
            } else {
                server.logger().debug("CreateRoomRequest: Generate Map");
                matchData.cacheMapGenerate(param3, param4, alias);
            }
        } else {
            matchData.room.goal = param1;
            matchData.room.timelimit = param2;
            matchData.room.weaponOption = param3;
            matchData.room.map = param4;
            matchData.room.isBreakInto = param5 != 0;
            matchData.room.isWanted = param7 != 0;
            matchData.room.isDropItem = param8 != 0;
            matchData.isBalance = param6 != 0;
        }

        if (roomType == RoomType.BUNGEE) {
            //matchData.CacheMap(regMaps.Find(x => x.Value.Map == param4).Value, new UserMapInfo(0, 0));
        }

        if (roomType == RoomType.BND) {
            // Unpack the timer configuration for Build and Destroy phases
            final int buildTime, destroyTime, repeat;
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

        if (roomType == RoomType.MAP_EDITOR) {
            SendCopyright(server, msgRef.client);
        }
    }

    public static void SendRendezvousInfo(final GameServerLogic server, final ClientReference client) {
        final MsgBody body = new MsgBody();

        body.write(0); //unused
        body.write(client.ip);
        body.write(client.port);

        server.say(new MsgReference(MessageId.CS_RENDEZVOUS_INFO_ACK, body, client));

        server.logger().debug("SendRendezvousInfo to: " + client.GetIdentifier());
    }

    private static void delegateMaster(final GameServerLogic logic, final MsgReference msgRef) {
        final MatchData matchData = msgRef.matchData;

        if (msgRef.client.seq == matchData.masterSeq) {
            final int newMaster = msgRef.msg.msg().readInt();

            logic.logger().debug("HandleDelegateMasterRequest from: " + msgRef.client.GetIdentifier());

            matchData.masterSeq = newMaster;
            SendMaster(logic, null, matchData);
        }
    }

    public static void SendMaster(final GameServerLogic server, final ClientReference client, final MatchData matchData) {
        final MsgBody body = new MsgBody();

        body.write(matchData.masterSeq);

        if (client == null) {
            server.say(new MsgReference(MessageId.CS_MASTER_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

            server.logger().debug("Broadcasted SendMaster for room no: " + matchData.room.no);
        }

        else {
            server.say(new MsgReference(MessageId.CS_MASTER_ACK, body, client));

            server.logger().debug("SendMaster to: " + client.GetIdentifier());
        }
    }

    public static void SendSlotLocks(final GameServerLogic server, final ClientReference client) {
        final MatchData matchData = client.matchData;
        for (byte i = 0; i < matchData.slots.size(); i++) {
            SendSlotLock(server, client, matchData, i);
        }

        server.logger().debug("SendSlots to: " + client.GetIdentifier());
    }

    public static void SendSlotLock(final GameServerLogic server, final ClientReference client, final MatchData matchData,
        final byte index) {
        SendSlotLock(server, client, matchData, index, SendType.UNICAST);
    }

    public static void SendSlotLock(final GameServerLogic server, final ClientReference client, final MatchData matchData, final byte index,
        final SendType sendType) {
        final MsgBody body = new MsgBody();

        body.write(index);
        body.write(matchData.slots.get(index).isLocked ? (byte) 1 : (byte) 0);
        server.say(new MsgReference(MessageId.CS_SLOT_LOCK_ACK, body, client, sendType, matchData.channel, matchData));

        if (sendType == SendType.UNICAST) {
            server.logger().debug("SendSlotLock to: " + client.GetIdentifier());
        } else {
            server.logger().debug("Broadcasted SendSlotLock for room no " + matchData.room.no);
        }
    }

    public static void SendEnter(final GameServerLogic server, final ClientReference client) {
        final MatchData matchData = client.matchData;

        final MsgBody body = new MsgBody();

        body.write(client.slot.slotIndex);
        body.write(client.seq);
        body.write(client.name);
        body.write(client.ip);   // local ip
        body.write(client.port); // local port
        body.write(client.ip);   // remote ip
        body.write(client.port); // remote port
        body.write(client.inventory.equipmentString.length);
        for (int i = 0; i < client.inventory.equipmentString.length; i++) {
            body.write(client.inventory.equipmentString[i]);
        }
        body.write(client.status.ordinal());
        body.write(client.data.xp);
        body.write(client.data.clanSeq);
        body.write(client.data.clanName);
        body.write(client.data.clanMark);
        body.write(client.data.rank);
        body.write((byte) 1); //playerflag
        body.write(client.inventory.weaponChgString.length);
        for (int i = 0; i < client.inventory.weaponChgString.length; i++) {
            body.write(client.inventory.weaponChgString[i]);
        }
        body.write(0); //drpItem count

        server.say(new MsgReference(MessageId.CS_ENTER_ACK, body, client, SendType.BROADCAST_ROOM_EXCLUSIVE, matchData.channel, matchData));
        server.logger().debug("Broadcasted SendEnter for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    public static void SendCopyright(final GameServerLogic server, final ClientReference client) {
        final MsgBody body = new MsgBody();

        final MatchData matchData = client.matchData;

        body.write(matchData.masterSeq);
        body.write(matchData.cachedUMI.slot);

        server.say(new MsgReference(MessageId.CS_COPYRIGHT_ACK, body, client));
        server.logger().debug("SendCopyRight to: " + client.GetIdentifier());
    }

    public static void SendAddRoom(final GameServerLogic server, final ClientReference client, final MatchData matchData) {
        final MsgBody body = new MsgBody();

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

        if (client == null) {
            server.say(new MsgReference(MessageId.CS_ADD_ROOM_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));
            server.logger().debug("Broadcasted SendAddRoom for room no: " + matchData.room.no);
        } else {
            server.say(new MsgReference(MessageId.CS_ADD_ROOM_ACK, body, client));
            server.logger().debug("SendAddRoom to: " + client.GetIdentifier());
        }
    }

    public static void SendCreateRoom(final GameServerLogic server, final ClientReference client) {
        SendCreateRoom(server, client, true);
    }

    public static void SendCreateRoom(final GameServerLogic server, final ClientReference client, final boolean success) {
        final MatchData matchData = client.matchData;

        final MsgBody body = new MsgBody();

        body.write(matchData.room.type.getId());
        body.write(success ? matchData.room.no : -1);
        body.write(matchData.room.title);

        server.say(new MsgReference(MessageId.CS_CREATE_ROOM_ACK, body, client));

        server.logger().debug("SendCreateRoom to: " + client.GetIdentifier());
    }

    private static void roomConfig(final GameServerLogic server, final MsgReference msgRef) {
        final MatchData matchData = msgRef.matchData;

        final int killCount = msgRef.msg.msg().readInt();
        final int timeLimit = msgRef.msg.msg().readInt();
        final int weaponOption = msgRef.msg.msg().readInt();
        final int nWhere = msgRef.msg.msg().readInt();
        final int breakInto = msgRef.msg.msg().readInt();
        final int teamBalance = msgRef.msg.msg().readInt();
        final int useBuildGun = msgRef.msg.msg().readInt();
        final int itemPickup = msgRef.msg.msg().readInt();
        final String whereAlias = msgRef.msg.msg().readString();
        final String pswd = msgRef.msg.msg().readString();
        final int type = msgRef.msg.msg().readInt();

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

        SendRoomConfig(server, null, matchData);
    }

    public static void SendRoomConfig(final GameServerLogic server, final ClientReference client, final MatchData matchData) {
        final MsgBody body = new MsgBody();

        body.write(matchData.room.map);
        body.write(matchData.room.curMapAlias);
        if (matchData.room.type == RoomType.MISSION) {
            body.write(matchData.room.goal); // core HP
        } else {
            body.write(matchData.room.weaponOption);
        }
        body.write(matchData.room.timelimit);
        body.write(matchData.room.goal);
        body.write(matchData.room.isBreakInto);
        body.write(matchData.isBalance);
        body.write(false); //useBuildGun
        body.write(""); //password
        body.write((byte) 0); //commented
        body.write(matchData.room.type.getId());
        body.write(matchData.room.isDropItem);
        body.write(matchData.room.isWanted);

        if (client == null) {
            server.say(new MsgReference(MessageId.CS_ROOM_CONFIG_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));
            server.logger().debug("Broadcasted SendRoomConfig for room no: " + matchData.room.no);
        } else {
            server.say(new MsgReference(MessageId.CS_ROOM_CONFIG_ACK, body, client));
            server.logger().debug("SendRoomConfig to: " + client.GetIdentifier());
        }
    }

    private static void roamIn(final GameServerLogic server, final MsgReference msgRef) {
        final int seq = msgRef.msg.msg().readInt();
        final int userType = msgRef.msg.msg().readInt();
        final boolean isWebPlayer = msgRef.msg.msg().readBool();
        final int language = msgRef.msg.msg().readInt();
        final String hashCode = msgRef.msg.msg().readString();

        server.logger().debug("HandleRoamin from: " + msgRef.client.GetIdentifier());

        ChannelHandlers.SendUserList(server, msgRef.client);
        SendRoamin(server, msgRef.client, msgRef.client.channel.channel.id);

        msgRef.client.clientStatus = ClientStatus.LOBBY;
    }

    public static void SendRoamin(final GameServerLogic server, final ClientReference client, final int dest) {
        SendRoamin(server, client, dest, SendType.UNICAST);
    }

    public static void SendRoamin(final GameServerLogic server, final ClientReference client, final int dest, final SendType sendType) {
        final MsgBody body = new MsgBody();

        body.write(dest);
        server.say(new MsgReference(MessageId.CS_ROAMIN_ACK, body, client, sendType));

        server.logger().debug("SendRoamin to: " + client.GetIdentifier());
    }

    public static void sendSlotData(final GameServerLogic server, final MatchData matchData) {
        final MsgBody body = new MsgBody();

        if (matchData == null || matchData.channel == null) {
            server.logger().debug("[SendSlotData] Client does not exist anymore");
            return;
        }

        body.write(matchData.clientList.size());

        for (final ClientReference client : matchData.clientList) {
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
        server.say(new MsgReference(MessageId.EXT_OP_SLOT_DATA_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        server.say(new MsgReference(MessageId.EXT_OP_SLOT_DATA_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        server.logger().debug("Broadcasted SendSlotData for room no: {0}", matchData.room.no);
    }

    private static void leave(final GameServerLogic server, final MsgReference msgRef) {
        final MatchData matchData = msgRef.matchData;

        server.logger().debug("HandleLeave from: " + msgRef.client.GetIdentifier());

        SendSetStatus(server, msgRef.client);
        SendLeave(server, msgRef.client);

        /*matchData.RemoveClient(msgRef.client);
        
        if (matchData.room.CurPlayer <= 0)
        {
            SendDeleteRoom(matchData, matchData.channel);
            msgRef.client.channel.RemoveMatch(matchData);
            return;
        }*/

        if (msgRef.client.seq == matchData.masterSeq && !matchData.clientList.isEmpty()) {
            matchData.masterSeq = matchData.clientList.getFirst().seq;
            SendMaster(server, null, matchData);
        }
    }

    private static void setStatus(final GameServerLogic logic, final MsgReference msgRef) {
        final int status = msgRef.msg.msg().readInt();

        msgRef.client.status = BrickManStatus.values()[status];

        logic.logger().debug("HandleSetStatusRequest from: " + msgRef.client.GetIdentifier());

        SendSetStatus(logic, msgRef.client);
    }

    public static void SendSetStatus(final GameServerLogic server, final ClientReference client) {
        final MatchData matchData = client.matchData;

        final MsgBody body = new MsgBody();

        body.write(client.seq);
        body.write(client.status.ordinal());

        server.say(new MsgReference(MessageId.CS_SET_STATUS_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        server.logger().debug("Broadcasted SendSetStatus for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    public static void SendLeave(final GameServerLogic server, final ClientReference client) {
        final MatchData matchData = client.matchData;

        if (matchData.clientList.contains(client)) {
            matchData.RemoveClient(client);
        }

        if (matchData.room.curPlayer <= 0) {
            SendDeleteRoom(server, matchData, matchData.channel);
            client.channel.removeMatch(matchData);
            return;
        }

        final MsgBody body = new MsgBody();

        body.write(client.seq);

        if (matchData == null || matchData.channel == null) {
            server.logger().debug("[SendLeave] Client left but was not in a room: " + client.GetIdentifier());
            return;
        }

        server.say(new MsgReference(MessageId.CS_LEAVE_ACK, body, client, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        server.logger().debug("Broadcasted SendLeave for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    public static void SendDeleteRoom(final GameServerLogic server, final MatchData matchData, final ChannelReference channel) {
        final MsgBody body = new MsgBody();

        body.write(matchData.room.no);

        server.say(new MsgReference(MessageId.CS_DEL_ROOM_ACK, body, null, SendType.BROADCAST_CHANNEL, channel, matchData));

        server.logger().debug("Broadcasted SendDelRoom for room no: " + matchData.room.no);
    }

    private static void join(final GameServerLogic server, final MsgReference msgRef) {
        final int roomNumber = msgRef.msg.msg().readInt();
        final String pswd = msgRef.msg.msg().readString();
        final boolean invite = msgRef.msg.msg().readBool();

        server.logger().debug("HandleJoin from: " + msgRef.client.GetIdentifier());

        final MatchData matchData = msgRef.client.channel.getMatchByRoomNumber(roomNumber);
        if (roomNumber == matchData.room.no) {
            matchData.AddClient(msgRef.client);

            SendJoin(server, msgRef.client);
            SendRendezvousInfo(server, msgRef.client);
            SendMaster(server, msgRef.client, matchData);
            SendSlotLocks(server, msgRef.client);
            SendRoomConfig(server, msgRef.client, matchData);
            SendAddRoom(server, msgRef.client, matchData);

            SendEnter(server, msgRef.client);

            sendSlotData(server, matchData);

            if (matchData.room.type == RoomType.MAP_EDITOR) {
                SendCopyright(server, msgRef.client);
            }
        }
    }

    public static void SendJoin(final GameServerLogic server, final ClientReference client) {
        final MatchData matchData = client.matchData;
        final MsgBody body = new MsgBody();

        body.write(matchData.room.no);
        server.say(new MsgReference(MessageId.CS_JOIN_ACK, body, client));

        server.logger().debug("SendJoin to: " + client.GetIdentifier());
    }

    private static void resume(final GameServerLogic server, final MsgReference msgRef) {
        final MatchData matchData = msgRef.matchData;

        final int nextStatus = msgRef.msg.msg().readInt();

        if (msgRef.client.seq == matchData.masterSeq) {
            matchData.room.status = RoomStatus.fromValue(nextStatus);
        }

        server.logger().debug("HandleResumeRoomRequest from: " + msgRef.client.GetIdentifier());

        sendRoom(server, null, matchData, SendType.BROADCAST_ROOM);
    }

    public static void sendRoom(final GameServerLogic server, final ClientReference client, final MatchData matchData) {
        sendRoom(server, client, matchData, SendType.UNICAST);
    }

    public static void sendRoom(final GameServerLogic server, final ClientReference client, final MatchData matchData,
        final SendType sendType) {
        final MsgBody body = new MsgBody();

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

        server.say(new MsgReference(MessageId.CS_ROOM_ACK, body, client, sendType, matchData.channel, matchData));
        if (sendType == SendType.UNICAST) {
            server.logger().debug("SendRoom to: " + client.GetIdentifier());
        } else {
            server.logger().debug("Broadcasted SendRoom for room no: " + matchData.room.no);
        }
    }

    private static void kick(final GameServerLogic server, final MsgReference msgRef) {
        //not tested/ server side remove?
        final int seq = msgRef.msg.msg().readInt();
        final ClientReference client = server.clientList.stream().filter(c -> c.seq == seq).findFirst().orElse(null);

        if (client != null) {
            msgRef.matchData.RemoveClient(client);
        }
        final MsgBody body = new MsgBody();
        body.write(seq);
        server.say(new MsgReference(MessageId.CS_KICK_ACK, body, msgRef.client, SendType.UNICAST));
    }

    private static void slotLock(final GameServerLogic server, final MsgReference msgRef) {
        final MatchData matchData = msgRef.matchData;

        final byte slotNum = msgRef.msg.msg().readByte();
        final byte lock = msgRef.msg.msg().readByte();

        server.logger().debug("HandleSlotLockRequest from: " + msgRef.client.GetIdentifier());

        matchData.slots.get(slotNum).toggleLock(lock != 0);
        final long count = matchData.slots.stream().filter(s -> !s.isLocked).count();
        matchData.room.maxPlayer = (int) count;
        SendSlotLock(server, msgRef.client, matchData, slotNum, SendType.BROADCAST_ROOM);
    }

    private static void teamChange(final GameServerLogic server, final MsgReference msgRef) {
        final MatchData matchData = msgRef.matchData;

        final boolean clickSlot = msgRef.msg.msg().readBool();
        final int slotNum = msgRef.msg.msg().readInt();

        server.logger().debug("HandleTeamChangeRequest from: " + msgRef.client.GetIdentifier());

        if (slotNum < -1 || slotNum > 15) {
            server.logger()
                .debug("[WARNING]: HandleTeamChangeRequest: Bad slot num " + slotNum + " from client: " + msgRef.client.GetIdentifier());
        } else if (slotNum == -1) {
            msgRef.client.AssignSlot(matchData.getNextFreeSlotOnOtherTeam(msgRef.client.slot));
        } else {
            msgRef.client.AssignSlot(matchData.slots.get(slotNum));
        }

        SendTeamChange(server, msgRef.client);
    }

    public static void SendTeamChange(final GameServerLogic server, final ClientReference client) {
        final MatchData matchData = client.matchData;

        final MsgBody body = new MsgBody();

        body.write(client.seq);
        body.write(0); //unused
        body.write(client.slot.slotIndex);

        server.say(new MsgReference(MessageId.CS_TEAM_CHANGE_ACK, body, client, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        server.logger().debug("Broadcasted SendTeamChange for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    private static void start(final GameServerLogic server, final MsgReference msgRef) {
        final MatchData matchData = msgRef.matchData;

        final int remainingCountdown = msgRef.msg.msg().readInt();

        matchData.lobbyCountdownTime = 0;

        server.logger().debug("HandleStartRequest from: " + msgRef.client.GetIdentifier());

        final boolean notReady = matchData.clientList.stream()
            .anyMatch(c -> c.status == BrickManStatus.PLAYER_WAITING && c.seq != matchData.masterSeq);

        if (notReady) {
            server.logger().debug("[WARNING]: HandleStartRequest: Not All Ready");
            return;
        }

        matchData.room.status = RoomStatus.PENDING;
        sendRoom(server, null, matchData, SendType.BROADCAST_ROOM);

        for (int i = 0; i < matchData.clientList.size(); i++) {
            matchData.clientList.get(i).status = BrickManStatus.PLAYER_LOADING;
            matchData.clientList.get(i).clientStatus = ClientStatus.MATCH;
            SendSetStatus(server, matchData.clientList.get(i));
            SendRespawnTicket(server, matchData.clientList.get(i));
        }

        SendStart(server, matchData);
    }

    public static void SendRespawnTicket(final GameServerLogic server, final ClientReference client) {
        final MsgBody body = new MsgBody();

        final int value = 1 + (int) (Math.random() * 63);
        body.write(value);

        server.say(new MsgReference(MessageId.CS_RESPAWN_TICKET_ACK, body, client));

        server.logger().debug("SendRespawnTicket to: " + client.GetIdentifier());
    }

    public static void SendStart(final GameServerLogic server, final MatchData matchData) {
        final MsgBody body = new MsgBody();

        body.write(matchData.lobbyCountdownTime);

        server.say(new MsgReference(MessageId.CS_START_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        server.logger().debug("Broadcasted SendStart for room no: " + matchData.room.no);
    }

}
