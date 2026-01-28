package de.brickforceaurora.gameserver.legacy.handler;

import de.brickforceaurora.gameserver.legacy.channel.BrickManStatus;
import de.brickforceaurora.gameserver.legacy.channel.ClientReference;
import de.brickforceaurora.gameserver.legacy.channel.ClientStatus;
import de.brickforceaurora.gameserver.legacy.combat.WeaponBy;
import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.handler.gamemodes.DefusionHandlers;
import de.brickforceaurora.gameserver.legacy.handler.gamemodes.IndividualHandlers;
import de.brickforceaurora.gameserver.legacy.handler.gamemodes.TeamHandlers;
import de.brickforceaurora.gameserver.legacy.handler.gamemodes.ZombieHandlers;
import de.brickforceaurora.gameserver.legacy.match.KillLogEntry;
import de.brickforceaurora.gameserver.legacy.match.MatchData;
import de.brickforceaurora.gameserver.legacy.protocol.MessageId;
import de.brickforceaurora.gameserver.legacy.protocol.MsgBody;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;
import de.brickforceaurora.gameserver.legacy.protocol.SendType;
import de.brickforceaurora.gameserver.legacy.room.RoomStatus;
import de.brickforceaurora.gameserver.legacy.room.RoomType;

import java.util.HashMap;
import java.util.Map;

public class MatchHandlers {

    public static void register(MessageDispatcher d) {
        d.register(MessageId.CS_WEAPON_HELD_RATIO_REQ.id(), MatchHandlers::weaponHeldRatio);
        d.register(MessageId.CS_LOAD_COMPLETE_REQ.id(), MatchHandlers::loadComplete);
        d.register(MessageId.CS_MATCH_COUNTDOWN_REQ.id(), MatchHandlers::matchCountdown);
        d.register(MessageId.CS_TIMER_REQ.id(), MatchHandlers::timer);
        d.register(MessageId.CS_INFLICTED_DAMAGE_REQ.id(), MatchHandlers::inflictedDamage);
        d.register(MessageId.CS_RESPAWN_TICKET_REQ.id(), MatchHandlers::respawnTicket);
        d.register(MessageId.CS_KILL_LOG_REQ.id(), MatchHandlers::killLog);
        d.register(MessageId.CS_RESULT_DONE_REQ.id(), MatchHandlers::resultDone);
        d.register(MessageId.CS_BREAK_INTO_REQ.id(), MatchHandlers::breakInto);
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

        logic.say(new MsgReference(MessageId.EXT_OP_POST_LOAD_INIT_ACK, body, client));

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

        logic.say(new MsgReference(MessageId.CS_DESTROYED_BRICK_ACK, body, client, sendType, matchData.channel, matchData));

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

        logic.say(new MsgReference(MessageId.CS_GET_CANNON_ACK, body, null, sendType, matchData.channel, matchData));

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

        logic.say(new MsgReference(MessageId.CS_GET_TRAIN_ACK, body, client, sendType, matchData.channel, matchData));


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
        logic.say(new MsgReference(MessageId.CS_MATCH_COUNTDOWN_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

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
        logic.say(new MsgReference(MessageId.CS_TIMER_ACK, body, client, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("SendTimer to: " + client.GetIdentifier());
    }

    private static void inflictedDamage(GameServerLogic logic, MsgReference msgRef)
    {
        ClientReference client = msgRef.client;
        MatchData matchData = msgRef.matchData;

        // How many entries the client sends
        int count = msgRef.msg.msg().readInt();

        if (count <= 0)
            return;

        int totalDamage = 0;

        for (int i = 0; i < count; i++)
        {
            int targetSeq = msgRef.msg.msg().readInt();
            int damage = msgRef.msg.msg().readInt();

            // Ignore invalid data
            if (damage <= 0)
                continue;

            // Damage must be applied to someone inside the same match
            ClientReference target = null;
            for (ClientReference c : matchData.clientList) {
                if (c.seq == targetSeq) {
                    target = c;
                    break;
                }
            }

            if (target == null)
                continue;

            // Prevent cheating (client should never send huge values)
            if (damage > 200)
                damage = 200;

            totalDamage += damage;
        }

        if (totalDamage <= 0)
            return;

        // -----------------------------------------
        //  REWARD CALCULATION (DAMAGE ONLY)
        // -----------------------------------------
        // Brick-Force rewarded small score per damage point.
        // Recommended balanced value:
        //
        // 1 score per ~7 damage.
        //
        // Damage reward formula:
        int reward = Math.max(1, totalDamage / 7);

        // Add to overall player score
        client.score += reward;

        // Optionally: Debug log
        // Console.WriteLine($"[InflictedDamage] {client.name} inflicted {totalDamage} → +{reward} score");

        MsgBody msg = new MsgBody();
        msg.write(client.seq);      // val  → player sequence ID
        msg.write(client.score);    // val2 → updated score

        logic.say(new MsgReference(MessageId.CS_ROUND_SCORE_ACK, msg, msgRef.client, SendType.UNICAST));
    }

    public static void respawnTicket(GameServerLogic logic, MsgReference msgRef)
    {
        logic.logger().debug("HandleRespawnTicketRequest from: " + msgRef.client.GetIdentifier());

        RoomHandlers.SendRespawnTicket(logic, msgRef.client);
    }

    private static void killLog(GameServerLogic logic, MsgReference msgRef)
    {
        if (logic.killLogTimer < 0.2f)
            return;

        MatchData matchData = msgRef.matchData;

        int id = msgRef.msg.msg().readInt();

        boolean exists = matchData.killLog.stream()
                .anyMatch(k -> k.id() == id);
        if (exists)
            return;

        if (id != matchData.lastKillLogId)
            matchData.lastKillLogId = id;
        else
            return;

        logic.killLogTimer = 0f;

        byte killerType = msgRef.msg.msg().readByte();
        int killer = msgRef.msg.msg().readInt();
        byte victimType = msgRef.msg.msg().readByte();
        int victim = msgRef.msg.msg().readInt();
        int weaponBy = msgRef.msg.msg().readInt();
        int slot = msgRef.msg.msg().readInt();
        int category = msgRef.msg.msg().readInt();
        int hitpart = msgRef.msg.msg().readInt();
        int damageLogCount = msgRef.msg.msg().readInt();

        Map<Integer, Integer> damageLog = new HashMap<>();
        for (int i = 0; i < damageLogCount; i++)
        {
            int key = msgRef.msg.msg().readInt();
            int value = msgRef.msg.msg().readInt();

            if (key == victim)
                continue;

            damageLog.merge(key, value, Integer::sum);
        }

        //Console.WriteLine("VictimType: " + victimType + " weaponBy: " + weaponBy);
        if (victimType == 1 || weaponBy == -1)
        {
            // We do NOT process mob kills here.
            // No kill feed, no score change, nothing.
            //update deathcunt for victim on mob kill
            return;
        }

        logic.logger().debug("HandleKillLogRequest from: " + msgRef.client.GetIdentifier());

        ClientReference victimClient = matchData.clientList.stream()
                .filter(c -> c.seq == victim)
                .findFirst()
                .orElse(null);

        if (victimClient == null)
            return;

        victimClient.deaths++;
        if (victimClient.slot.slotIndex < 8) // Blue team
        {
            if (!matchData.deadBluePlayers.contains(victimClient.seq))
                matchData.deadBluePlayers.add(victimClient.seq);
        }
        else // Red team
        {
            if (!matchData.deadRedPlayers.contains(victimClient.seq))
                matchData.deadRedPlayers.add(victimClient.seq);
        }
        SendDeathCount(logic, victimClient);

        if (killer == victim && !damageLog.isEmpty()) {
            killer = damageLog.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(killer);
        }

        int finalKiller = killer;
        ClientReference killerClient = matchData.clientList.stream()
                .filter(c -> c.seq == finalKiller)
                .findFirst()
                .orElse(null);

        if (killerClient != null && killer != victim) {
            killerClient.kills++;
            SendKillCount(logic, killerClient);
        }

        for (Map.Entry<Integer, Integer> e : damageLog.entrySet()) {
            int seq = e.getKey();
            int dmg = e.getValue();

            if (seq == victim)
                continue;

            ClientReference assist = matchData.clientList.stream()
                    .filter(c -> c.seq == seq)
                    .findFirst()
                    .orElse(null);

            if (assist == null)
                continue;

            if (seq != killer) {
                assist.assists++;
                assist.score += dmg;
                SendAssistCount(logic, assist);
            } else if (killerClient != null) {
                killerClient.score += dmg;
                SendRoundScore(logic, killerClient);
            }
        }

        //Fix for hosting killing fall damage?
        // does not work
        if (weaponBy == 0)
        {
            killerType = 0;
            killer = 0;
        }
        KillLogEntry killLogEntry = new KillLogEntry(id, killerType, killer, victimType, victim, WeaponBy.fromValue(weaponBy), slot, category, hitpart, damageLog);
        matchData.killLog.add(killLogEntry);
        SendKillLogEntry(logic, killLogEntry, matchData);

        if (killer != victim)
        {
            switch (matchData.room.type)
            {
                case TEAM_MATCH:
                    if (victimClient.slot.slotIndex > 7)
                        matchData.redScore++;
                    else
                        matchData.blueScore++;
                    TeamHandlers.SendTeamScore(logic, matchData);
                    if (matchData.blueScore >= matchData.room.goal || matchData.redScore >= matchData.room.goal)
                    {
                        TeamHandlers.handleEnd(logic, matchData);
                    }
                    break;

                case INDIVIDUAL:
                    matchData.redScore++;
                    IndividualHandlers.SendIndividualScore(logic, matchData);

                    if (matchData.redScore >= matchData.room.goal)
                    {
                        IndividualHandlers.handleEnd(logic, matchData);
                    }
                    break;

                case BND:
                    logic.logger().debug("[WARNING]: " + matchData.isBuildPhase);
                    //the emulator match data is currently in the wrong phase?
                    if (!matchData.isBuildPhase)
                    {
                        // Score during the Destroy phase
                        if (victimClient.slot.slotIndex > 7)
                            matchData.redScore++;
                        else
                            matchData.blueScore++;

                        //BND.SendBnDScore(matchData);

                        if (matchData.blueScore >= matchData.room.goal || matchData.redScore >= matchData.room.goal)
                        {
                            //BND.HandleBNDMatchEnd(matchData);
                        }
                    }
                    break;

                case CAPTURE_THE_FLAG:
                    if (victimClient.slot.slotIndex > 7)
                        matchData.redKillCount++;
                    else
                        matchData.blueKillCount++;
                    TeamHandlers.SendTeamScore(logic, matchData);
                    if (matchData.blueScore >= matchData.room.goal || matchData.redScore >= matchData.room.goal)
                    {
                        //CTF.HandleCTFMatchEnd(matchData);
                    }
                    break;

                case EXPLOSION:
                    //this needs fixing
                    //we need to check that score does not get added on explosion through bomb
                    //maybe if type == bomb?
                    if(WeaponBy.fromValue(weaponBy) == WeaponBy.CLOCKBOMB)
                        break;

                    int totalRed  = (int) matchData.redSlots.stream().filter(s -> s.isUsed).count();
                    int totalBlue = (int) matchData.blueSlots.stream().filter(s -> s.isUsed).count();

                    //SendScore!!
                    TeamHandlers.SendTeamScore(logic, matchData);

                    if (matchData.deadBluePlayers.size() >= totalBlue) {
                        matchData.blueScore++;
                        DefusionHandlers.roundEnd(logic, msgRef, (byte) 1);
                    } else if (matchData.deadRedPlayers.size() >= totalRed) {
                        matchData.redScore++;
                        DefusionHandlers.roundEnd(logic, msgRef, (byte) 0);
                    }
                    break;

                case ZOMBIE:
//                    if (HitPart.TYPE.values()[hitpart] == HitPart.TYPE.BRAIN && matchData.zombiePlayers.contains(victim))
//                    {
//                        matchData.zombiePlayers.remove(victim);
//                        matchData.killedPlayers.add(victim);
//                        if (matchData.zombiePlayers.isEmpty())
//                        {
//                            ZombieHandlers.roundEnd(logic, msgRef, matchData);
//                        }
//                    }
                    break;

                case BUNGEE:
                    matchData.redScore++;
                    //Freefall.SendFreefallScore(matchData);

                    if (matchData.redScore >= matchData.room.goal)
                    {
                        //Freefall.HandleMatchEnd(matchData);
                    }
                    break;
            }
        }
    }

    public static void SendDeathCount(GameServerLogic logic, ClientReference client)
    {
        MatchData matchData = client.matchData;

        MsgBody body = new MsgBody();

        body.write(client.seq);
        body.write(client.deaths);

        logic.say(new MsgReference(MessageId.CS_DEATH_COUNT_ACK, body, null, SendType.BROADCAST_ROOM, client.channel, client.matchData));

        logic.logger().debug("Broadcasted SendDeatchCount for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    public static void SendKillCount(GameServerLogic logic, ClientReference client)
    {
        MatchData matchData = client.matchData;

        MsgBody body = new MsgBody();

        body.write(client.seq);
        body.write(client.kills);

        logic.say(new MsgReference(MessageId.CS_KILL_COUNT_ACK, body, null, SendType.BROADCAST_ROOM, client.channel, client.matchData));

        logic.logger().debug("Broadcasted SendKillCount for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    public static void SendRoundScore(GameServerLogic logic, ClientReference client)
    {
        MatchData matchData = client.matchData;

        MsgBody body = new MsgBody();

        body.write(client.seq);
        body.write(client.score);

        logic.say(new MsgReference(MessageId.CS_ROUND_SCORE_ACK, body, null, SendType.BROADCAST_ROOM, client.channel, client.matchData));

        logic.logger().debug("Broadcasted SendRoundScore for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    public static void SendKillLogEntry(GameServerLogic logic, KillLogEntry entry, MatchData matchData)
    {
        MsgBody body = new MsgBody();

        body.write(entry.id());
        body.write(entry.killerType());
        body.write(entry.killer());
        body.write(entry.victimType());
        body.write(entry.victim());
        body.write(entry.weaponBy().getId());
        body.write(entry.hitpart());

        logic.say(new MsgReference(MessageId.CS_KILL_LOG_ACK, body, null, SendType.BROADCAST_ROOM, matchData.channel, matchData));

        logic.logger().debug("Broadcasted SendKillLogEntry for room no: " + matchData.room.no);
    }

    public static void SendAssistCount(GameServerLogic logic, ClientReference client)
    {
        MatchData matchData = client.matchData;

        MsgBody body = new MsgBody();

        body.write(client.seq);
        body.write(client.assists);
        body.write(client.score);

        logic.say(new MsgReference(MessageId.CS_ASSIST_COUNT_ACK, body, null, SendType.BROADCAST_ROOM, client.channel, client.matchData));

        logic.logger().debug("Broadcasted SendAssistCount for client " + client.GetIdentifier() + " for room no: " + matchData.room.no);
    }

    private static void resultDone(GameServerLogic logic, MsgReference msgRef)
    {
        msgRef.client.status = BrickManStatus.PLAYER_WAITING;
        msgRef.client.clientStatus = ClientStatus.ROOM;

        logic.logger().debug("HandleResultDoneRequest from: " + msgRef.client.GetIdentifier());

        RoomHandlers.SendSetStatus(logic, msgRef.client);
    }

    private static void breakInto(GameServerLogic logic, MsgReference msgRef)
    {
        logic.logger().debug("HandleBreakInto from: " + msgRef.client.GetIdentifier());

        MatchData matchData = msgRef.matchData;

        int reply = 0;

        if (!matchData.room.isBreakInto)
            reply = -1;

        else if (matchData.room.status != RoomStatus.PLAYING)
            reply = -2;

        else
        {
            msgRef.client.status = BrickManStatus.PLAYER_LOADING;
            msgRef.client.clientStatus = ClientStatus.MATCH;
            RoomHandlers.SendSetStatus(logic, msgRef.client);
            TeamHandlers.SendTeamScore(logic, matchData);
            for (int i = 0; i < matchData.clientList.size(); i++)
            {
                SendKillCount(logic, matchData.clientList.get(i));
                SendDeathCount(logic, matchData.clientList.get(i));
            }
            msgRef.client.isBreakingInto = true;
        }

        SendBreakInto(logic, msgRef.client, reply);
    }

    public static void SendBreakInto(GameServerLogic logic, ClientReference client, int reply)
    {
        MsgBody body = new MsgBody();

        body.write(reply);

        logic.say(new MsgReference(MessageId.CS_BREAK_INTO_ACK, body, client));

        logic.logger().debug("SendBreakInto to: " + client.GetIdentifier());
    }

}
