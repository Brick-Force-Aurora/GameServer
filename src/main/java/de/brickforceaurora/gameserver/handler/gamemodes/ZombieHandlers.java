package de.brickforceaurora.gameserver.handler.gamemodes;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.handler.RoomHandlers;
import de.brickforceaurora.gameserver.match.MatchData;
import de.brickforceaurora.gameserver.net.MsgReference;
import de.brickforceaurora.gameserver.net.SendType;
import de.brickforceaurora.gameserver.protocol.MessageId;
import de.brickforceaurora.gameserver.protocol.MsgBody;
import de.brickforceaurora.gameserver.room.RoomStatus;

public class ZombieHandlers {

    public static void roundEnd(GameServerLogic logic, MsgReference msgRef, MatchData data)
    {
        logic.logger().debug("Send RoundEnd client {0} data: {1}", msgRef.client.GetIdentifier(), data.toString());

        if (data.zombieCurrentRound >= data.room.goal)
        {
            data.EndMatch(logic);
            return;
        }

        MsgBody msg = new MsgBody();
        msg.write(data.zombieCurrentRound);
        if (data.humanPlayers.size() == 0)
        {
            //Zombies win
            msg.write((byte)-1);
            msg.write((byte)1);
            msg.write(0); //roundcode (unused in zombie)
        }
        else
        {
            //Humans win
            msg.write((byte)1);
            msg.write((byte)-1);
            msg.write(0); //roundcode (unused in zombie)
        }
        data.ResetForNewRound();
        data.zombieCurrentRound += 1;
        data.zombieRoundsLeft -= 1;

        HandleZombieScoreRequest(logic, msgRef);

        logic.say(new MsgReference(MessageId.CS_ROUND_END_ACK.getId(), msg, msgRef.client, SendType.BROADCAST_ROOM, data.channel, data));
    }

    private static void HandleZombieScoreRequest(GameServerLogic logic, MsgReference msgRef)
    {
        logic.logger().debug("Zombie Score Request: Total Rounds: {0} Current Round: {0}", msgRef.matchData.room.goal, msgRef.matchData.zombieCurrentRound);

        // Prepare the message body with total rounds and current round
        MsgBody msg = new MsgBody();
        msg.write(msgRef.matchData.room.goal); // Total rounds
        msg.write(msgRef.matchData.zombieCurrentRound); // Current round

        logic.say(new MsgReference(MessageId.CS_ZOMBIE_MODE_SCORE_ACK.getId(), msg, null, SendType.BROADCAST_ROOM, msgRef.matchData.channel, msgRef.matchData));
    }

    public static void handleEnd(GameServerLogic logic, MatchData matchData)
    {
        matchData.room.status = RoomStatus.WAITING;
        SendZombieMatchEnd(logic, matchData);
        matchData.Reset();
        RoomHandlers.sendRoom(logic, null, matchData, SendType.BROADCAST_ROOM);
    }

    private static void SendZombieMatchEnd(GameServerLogic logic, MatchData matchData)
    {
        MsgBody body = new MsgBody();

        body.write(matchData.clientList.size());
        for (int i = 0; i < matchData.clientList.size(); i++)
        {
            int points = (int)(matchData.clientList.get(i).score * 0.4);
            int xp = (int)(matchData.clientList.get(i).score * 0.5);
            body.write(matchData.clientList.get(i).slot.isRed);
            body.write(matchData.clientList.get(i).seq);
            body.write(matchData.clientList.get(i).name);
            body.write(0); // survival ensured
            body.write(0); // zombie victory
            body.write(matchData.clientList.get(i).assists);
            body.write(matchData.clientList.get(i).score);
            body.write(points); //points
            body.write(xp); //xp
            body.write(0); //mission
            body.write(matchData.clientList.get(i).data.xp);
            body.write(matchData.clientList.get(i).data.xp + xp);
            body.write((long)0); //buff
        }

        logic.say(new MsgReference(MessageId.CS_ZOMBIE_END_ACK.getId(), body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendZombieMatchEnd for room no: " + matchData.room.no);
    }
}
