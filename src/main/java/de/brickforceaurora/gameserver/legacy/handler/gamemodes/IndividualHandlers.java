package de.brickforceaurora.gameserver.legacy.handler.gamemodes;

import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.handler.RoomHandlers;
import de.brickforceaurora.gameserver.legacy.match.MatchData;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;
import de.brickforceaurora.gameserver.legacy.protocol.SendType;
import de.brickforceaurora.gameserver.legacy.room.RoomStatus;

public class IndividualHandlers {
    public static void handleEnd(GameServerLogic logic, MatchData matchData)
    {
        matchData.room.status = RoomStatus.WAITING;
        SendIndividualMatchEnd(logic, matchData);
        matchData.Reset();
        RoomHandlers.sendRoom(logic,null, matchData, SendType.BROADCAST_ROOM);
    }

    public static void SendIndividualMatchEnd(GameServerLogic logic, MatchData matchData)
    {
        MsgBody body = new MsgBody();

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
        logic.say(new MsgReference(MessageId.CS_INDIVIDUAL_MATCH_END_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendIndivudalMatchEnd for room no: " + matchData.room.no);
    }

    public static void SendIndividualScore(GameServerLogic logic, MatchData matchData)
    {
        MsgBody body = new MsgBody();

        body.write(matchData.redScore);

        logic.say(new MsgReference(MessageId.CS_INDIVIDUAL_SCORE_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendIndividualScore for room no: " + matchData.room.no);
    }
}
