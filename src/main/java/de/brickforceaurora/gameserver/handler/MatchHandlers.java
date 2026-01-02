package de.brickforceaurora.gameserver.handler;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.handler.gamemodes.DefusionHandlers;
import de.brickforceaurora.gameserver.handler.gamemodes.ZombieHandlers;
import de.brickforceaurora.gameserver.match.MatchData;
import de.brickforceaurora.gameserver.net.BrickManStatus;
import de.brickforceaurora.gameserver.net.ClientReference;
import de.brickforceaurora.gameserver.net.MsgReference;
import de.brickforceaurora.gameserver.net.SendType;
import de.brickforceaurora.gameserver.protocol.ExtensionOpcodes;
import de.brickforceaurora.gameserver.protocol.MessageId;
import de.brickforceaurora.gameserver.protocol.MsgBody;
import de.brickforceaurora.gameserver.room.RoomStatus;
import de.brickforceaurora.gameserver.room.RoomType;

import java.util.Map;

public class MatchHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.CS_WEAPON_HELD_RATIO_REQ.getId(), MatchHandlers::weaponHeldRatio);
        d.register(MessageId.CS_LOAD_COMPLETE_REQ.getId(), MatchHandlers::loadComplete);
        d.register(MessageId.CS_MATCH_COUNTDOWN_REQ.getId(), MatchHandlers::matchCountdown);
        d.register(MessageId.CS_TIMER_REQ.getId(), MatchHandlers::timer);
    }

    private static void weaponHeldRatio(GameServerLogic logic, MsgReference msgRef)
    {
        MatchData matchData = msgRef.matchData;

        int count = msgRef.msg.msg().readInt();
        for (int i = 0; i < count; i++)
        {
            long key = msgRef.msg.msg().readLong();
            float value = msgRef.msg.msg().readFloat();
        }

        logic.logger().debug("HandleWeaponHeldRatioRequest from: " + msgRef.client.GetIdentifier());

        if (msgRef.client.status.ordinal() <= BrickManStatus.PLAYER_LOADING.ordinal())
        {
            msgRef.client.status = BrickManStatus.PLAYER_PLAYING;
            RoomHandlers.SendSetStatus(logic, msgRef.client);
            SendPostLoadInit(logic, msgRef.client);
        }

        if (msgRef.client.isBreakingInto)
        {
            msgRef.client.isBreakingInto = false;

            for (int i = 0; i < matchData.destroyedBricks.size(); i++)
                SendDestroyedBrick(logic, msgRef.client, matchData.destroyedBricks.get(i), matchData);

            SendCannons(logic, msgRef.client);
            SendTrains(logic, msgRef.client);
        }
    }

    public static void SendPostLoadInit(GameServerLogic logic, ClientReference client)
    {
        MsgBody body = new MsgBody();

        logic.say(new MsgReference(ExtensionOpcodes.OP_POST_LOAD_INIT_ACK.getOpCode(), body, client));

        logic.logger().debug("SendPostLoadInit to: " + client.GetIdentifier());
    }

    public static void SendDestroyedBrick(GameServerLogic logic, ClientReference client, int brick, MatchData matchData)
    {
        SendDestroyedBrick(logic, client, brick, matchData, SendType.UNICAST);
    }

    public static void SendDestroyedBrick(GameServerLogic logic, ClientReference client, int brick, MatchData matchData, SendType sendType)
    {
        MsgBody body = new MsgBody();

        body.write(brick);

        logic.say(new MsgReference(78, body, client, sendType, matchData.channel, matchData));

        if (sendType == SendType.UNICAST)
            logic.logger().debug("SendDestroyedBrick to: " + client.GetIdentifier());
        else
            logic.logger().debug("Broadcasted SendDestroyedBrick for brick for room no: " + matchData.room.no);
    }

    public static void SendCannons(GameServerLogic logic, ClientReference client) {
        MatchData matchData = client.matchData;

        for (Map.Entry<Integer, Integer> entry : matchData.usedCannons.entrySet()) {
            SendGetCannon(
                    logic,
                    entry.getValue(),
                    entry.getKey(),
                    matchData,
                    client,
                    SendType.UNICAST
            );
        }
    }

    public static void SendTrains(GameServerLogic logic, ClientReference client) {
        MatchData matchData = client.matchData;

        for (Map.Entry<Integer, Integer> entry : matchData.usedTrains.entrySet()) {
            SendGetTrain(
                    logic,
                    entry.getValue(),
                    entry.getKey(),
                    matchData,
                    client,
                    SendType.UNICAST
            );
        }
    }

    public static void SendGetCannon(GameServerLogic logic, int seq, int brickSeq, MatchData matchData, ClientReference client, SendType sendType)
    {
        MsgBody body = new MsgBody();

        body.write(seq);
        body.write(brickSeq);

        logic.say(new MsgReference(159, body, null, sendType, matchData.channel, matchData));

        if (sendType == SendType.BROADCAST_ROOM)
            logic.logger().debug("Broadcasted SendGetCannon for room no: " + matchData.room.no);
        else
            logic.logger().debug("SendGetCannon to: " + client.GetIdentifier());
    }

    public static void SendGetTrain(GameServerLogic logic, int seq, int trainId, MatchData matchData, ClientReference client, SendType sendType)
    {
        MsgBody body = new MsgBody();

        body.write(seq);
        body.write(trainId);

        logic.say(new MsgReference(552, body, client, sendType, matchData.channel, matchData));


        if (sendType == SendType.BROADCAST_ROOM)
            logic.logger().debug("Broadcasted SendGetTrain for room no: " + matchData.room.no);
        else
            logic.logger().debug("SendGetTrain to: " + client.GetIdentifier());
    }

    private static void loadComplete(GameServerLogic logic, MsgReference msgRef)
    {
        int crc = msgRef.msg.msg().readInt();

        msgRef.client.isLoaded = true;

        logic.logger().debug("HandleLoadComplete from: " + msgRef.client.GetIdentifier());
    }

    private static void matchCountdown(GameServerLogic logic, MsgReference msgRef)
    {
        MatchData matchData = msgRef.matchData;

        int countdownTime = msgRef.msg.msg().readInt();

        logic.logger().debug("HandleMatchCountdown from: " + msgRef.client.GetIdentifier());

        if (msgRef.client.seq == matchData.masterSeq)
        {
            matchData.countdownTime = countdownTime;
            if (matchData.countdownTime <= 0)
            {
                matchData.room.status = RoomStatus.PLAYING;
                RoomHandlers.sendRoom(logic,null, matchData, SendType.BROADCAST_ROOM);
            }

            SendMatchCountdown(logic, matchData);
        }
    }

    public static void SendMatchCountdown(GameServerLogic logic, MatchData matchData)
    {
        MsgBody body = new MsgBody();

        body.write(matchData.countdownTime);
        logic.say(new MsgReference(72, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendMatchCountdown for: " + matchData.countdownTime);
    }

    private static void timer(GameServerLogic logic, MsgReference msgRef)
    {
        MatchData matchData = msgRef.matchData;

        int remainTime = msgRef.msg.msg().readInt();
        int playTime = msgRef.msg.msg().readInt();

        if (msgRef.client.seq == matchData.masterSeq)
        {
            matchData.remainTime = remainTime;
            matchData.playTime = playTime;
        }

        logic.logger().debug("HandleTimer from: " + msgRef.client.GetIdentifier());
        if (matchData.room.type == RoomType.BND)
        {
            if (matchData.repeat <= 0)
            {
                matchData.EndMatch(logic);
            }
        }
        else if (matchData.room.type == RoomType.EXPLOSION)
        {
            if (matchData.remainTime <= 0)
            {
                if (matchData.redScore >= matchData.room.goal || matchData.blueScore >= matchData.room.goal)
                {
                    matchData.EndMatch(logic);
                }
                else
                {
                    //round code blue team wins when time runs out
                    //this could be wrong and needs to be checked and referenced in exlplosionMatch.cs
                    matchData.blueScore++;
                    DefusionHandlers.roundEnd(logic, msgRef, (byte) 1);
                }
            }
        }
        else if (matchData.room.type == RoomType.ZOMBIE)
        {
            if (matchData.remainTime <= 0 && matchData.zombieRoundsLeft >= 0)
            {
                ZombieHandlers.roundEnd(logic, msgRef, matchData);
            }
        }
        else
        {
            if (matchData.remainTime <= 0)
                matchData.EndMatch(logic);
        }

        SendTimer(logic, msgRef.client);
    }

    public static void SendTimer(GameServerLogic logic, ClientReference client)
    {
        MatchData matchData = client.matchData;

        MsgBody body = new MsgBody();

        body.write(matchData.remainTime);
        body.write(matchData.playTime);
        logic.say(new MsgReference(66, body, client, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("SendTimer to: " + client.GetIdentifier());
    }
}
