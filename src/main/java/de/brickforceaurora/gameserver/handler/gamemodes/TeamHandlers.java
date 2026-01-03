package de.brickforceaurora.gameserver.handler.gamemodes;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.handler.RoomHandlers;
import de.brickforceaurora.gameserver.match.MatchData;
import de.brickforceaurora.gameserver.net.MsgReference;
import de.brickforceaurora.gameserver.net.SendType;
import de.brickforceaurora.gameserver.protocol.MsgBody;
import de.brickforceaurora.gameserver.room.RoomStatus;

public class TeamHandlers {
    public static void handleEnd(GameServerLogic logic, MatchData matchData)
    {
        matchData.room.status = RoomStatus.WAITING;
        SendTeamMatchEnd(logic, matchData);
        matchData.Reset();
        RoomHandlers.sendRoom(logic,null, matchData, SendType.BROADCAST_ROOM);
    }

    public static void SendTeamMatchEnd(GameServerLogic logic, MatchData matchData)
    {
        for (int team = 0; team < 2; team++)
        {
            MsgBody body = new MsgBody();

            body.write(team == 0 ? matchData.GetWinningTeam() : (byte)-matchData.GetWinningTeam());
            body.write(matchData.redScore);
            body.write(matchData.blueScore);
            body.write(matchData.blueScore);
            body.write(matchData.redScore);
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
            logic.say(new MsgReference(70, body, null, team == 0 ? SendType.BROADCAST_BLUE_TEAM : SendType.BROADCAST_RED_TEAM));
        }

        logic.logger().debug("Broadcasted SendTeamMatchEnd for room no: " + matchData.room.no);
    }

    public static void SendTeamScore(GameServerLogic logic, MatchData matchData)
    {
        MsgBody body = new MsgBody();
        body.write(matchData.redScore);
        body.write(matchData.blueScore);

        logic.say(new MsgReference(67, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendTeamScore for room no: " + matchData.room.no);
    }
}
