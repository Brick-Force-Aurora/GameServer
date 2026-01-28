package de.brickforceaurora.gameserver.legacy.handler.gamemodes;

import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.handler.RoomHandlers;
import de.brickforceaurora.gameserver.legacy.match.MatchData;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;
import de.brickforceaurora.gameserver.legacy.protocol.SendType;
import de.brickforceaurora.gameserver.legacy.room.RoomStatus;

public class ZombieHandlers {

    public static void roundEnd(final GameServerLogic logic, final MsgReference msgRef, final MatchData data) {
        logic.logger().debug("Send RoundEnd client {0} data: {1}", msgRef.client.GetIdentifier(), data.toString());

        if (data.zombieCurrentRound >= data.room.goal) {
            data.EndMatch(logic);
            return;
        }

        final MsgBody msg = new MsgBody();
        msg.write(data.zombieCurrentRound);
        if (data.humanPlayers.size() == 0) {
            //Zombies win
            msg.write((byte) -1);
            msg.write((byte) 1);
        } else {
            //Humans win
            msg.write((byte) 1);
            msg.write((byte) -1);
        }
        msg.write(0); //roundcode (unused in zombie)
        data.ResetForNewRound();
        data.zombieCurrentRound += 1;
        data.zombieRoundsLeft -= 1;

        HandleZombieScoreRequest(logic, msgRef);

        logic.say(new MsgReference(MessageId.CS_ROUND_END_ACK, msg, msgRef.client, SendType.BROADCAST_ROOM, data.channel, data));
    }

    private static void HandleZombieScoreRequest(final GameServerLogic logic, final MsgReference msgRef) {
        logic.logger().debug("Zombie Score Request: Total Rounds: {0} Current Round: {0}", msgRef.matchData.room.goal,
            msgRef.matchData.zombieCurrentRound);

        // Prepare the message body with total rounds and current round
        final MsgBody msg = new MsgBody();
        msg.write(msgRef.matchData.room.goal); // Total rounds
        msg.write(msgRef.matchData.zombieCurrentRound); // Current round

        logic.say(new MsgReference(MessageId.CS_ZOMBIE_MODE_SCORE_ACK, msg, null, SendType.BROADCAST_ROOM, msgRef.matchData.channel,
            msgRef.matchData));
    }

    public static void handleEnd(final GameServerLogic logic, final MatchData matchData) {
        matchData.room.status = RoomStatus.WAITING;
        SendZombieMatchEnd(logic, matchData);
        matchData.Reset();
        RoomHandlers.sendRoom(logic, null, matchData, SendType.BROADCAST_ROOM);
    }

    private static void SendZombieMatchEnd(final GameServerLogic logic, final MatchData matchData) {
        final MsgBody body = new MsgBody();

        body.write(matchData.clientList.size());
        for (int i = 0; i < matchData.clientList.size(); i++) {
            final int points = (int) (matchData.clientList.get(i).score * 0.4);
            final int xp = (int) (matchData.clientList.get(i).score * 0.5);
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
            body.write((long) 0); //buff
        }

        logic.say(new MsgReference(MessageId.CS_ZOMBIE_END_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendZombieMatchEnd for room no: " + matchData.room.no);
    }
}
