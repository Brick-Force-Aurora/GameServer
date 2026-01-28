package de.brickforceaurora.gameserver.legacy.handler.gamemodes;

import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.handler.RoomHandlers;
import de.brickforceaurora.gameserver.legacy.match.MatchData;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;
import de.brickforceaurora.gameserver.legacy.protocol.SendType;
import de.brickforceaurora.gameserver.legacy.room.RoomStatus;

public class DefusionHandlers {
    public static void roundEnd(GameServerLogic logic, MsgReference msgRef, byte roundCode)
    {
        MatchData data = msgRef.matchData;
        MsgBody msg = new MsgBody();
        msg = new MsgBody();
        msg.write(data.currentRound); //on Round End
        if (roundCode == 0)
        {
            msg.write(1); //endCode4Red
            msg.write(-1); //endCode4Blue
        } else
        {
            msg.write(-1); //endCode4Red
            msg.write(1); //endCode4Blue
        }
        msg.write(roundCode); //roundCode
        logic.say(new MsgReference(MessageId.CS_ROUND_END_ACK, msg, msgRef.client, SendType.BROADCAST_ROOM, data.channel, data));
        SendScore(logic, data);

        if (data.redScore >= data.room.goal || data.blueScore >= data.room.goal)
        {
            logic.logger().debug("Explosion match end: goal reached.");
            data.EndMatch(logic);
            return;
        }

        // Schedule round reset
        data.ResetForNewRound();
    }

    private static void SendScore(GameServerLogic logic, MatchData matchData)
    {
        MsgBody body = new MsgBody();
        body.write(matchData.redScore);
        body.write(matchData.blueScore);

        logic.say(new MsgReference(MessageId.CS_TEAM_SCORE_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendDefusionScore for room no: " + matchData.room.no);
    }

    public static void handleEnd(GameServerLogic logic, MatchData matchData)
    {
        matchData.room.status = RoomStatus.WAITING;
        SendMatchEnd(logic, matchData);
        matchData.Reset();
        RoomHandlers.sendRoom(logic, null, matchData, SendType.BROADCAST_ROOM);
    }

    private static void SendMatchEnd(GameServerLogic logic, MatchData matchData)
    {
        //fix this
        for (int team = 0; team < 2; team++)
        {
            MsgBody body = new MsgBody();

            body.write(team == 0 ? matchData.GetWinningTeam() : (byte)-matchData.GetWinningTeam());
            body.write(matchData.redScore); //RedScore
            body.write(matchData.blueScore); //BlueScore
            body.write(0); //RedMission
            body.write(0); //BlueMission
            body.write(matchData.redKillCount); //RedTotalKill
            body.write(matchData.blueKillCount); //BluTotalKill
            body.write(matchData.blueKillCount); //RedTotalDeath
            body.write(matchData.redKillCount); //BlueTotalDeath
            body.write(matchData.clientList.size());
            for (int i = 0; i < matchData.clientList.size(); i++)
            {
                body.write(matchData.clientList.get(i).slot.isRed);
                body.write(matchData.clientList.get(i).seq);
                body.write(matchData.clientList.get(i).name);
                body.write(matchData.clientList.get(i).kills);
                body.write(matchData.clientList.get(i).deaths);
                body.write(matchData.clientList.get(i).assists);
                body.write(matchData.clientList.get(i).score);
                body.write(0); //points
                body.write(0); //xp
                body.write(0); //mission
                body.write(matchData.clientList.get(i).data.xp);
                body.write(matchData.clientList.get(i).data.xp);
                body.write((long)0); //buff
            }
            logic.say(new MsgReference(MessageId.CS_BLAST_MODE_END_ACK, body, null, team == 0 ? SendType.BROADCAST_BLUE_TEAM : SendType.BROADCAST_RED_TEAM));
        }

        logic.logger().debug("Broadcasted SendDefusionMatchEnd for room no: " + matchData.room.no);
    }
}
