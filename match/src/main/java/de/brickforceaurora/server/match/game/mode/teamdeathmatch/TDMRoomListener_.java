package de.brickforceaurora.server.match.game.mode.teamdeathmatch;

import java.util.ArrayList;
import java.util.List;

import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.match.game.GameTarget;
import de.brickforceaurora.server.match.game.IGameNetListener;
import de.brickforceaurora.server.match.game.PlayerStats;
import de.brickforceaurora.server.match.game.TeamGameData;
import de.brickforceaurora.server.match.listener.ClientListener;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.protocol.data.DamageEntry;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.serverbound.original.*;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(TeamDeathMatchMode.class)
public class TDMRoomListener_ implements IGameNetListener<TDMData> {

    private final TeamDeathMatchMode mode;

    public TDMRoomListener_(TeamDeathMatchMode mode) {
        this.mode = mode;
    }

    @PacketHandler
    public void handleMyExamplePacket(Room room, TDMData data, NetContext<ServerboundAcceptApplicantPacket> context) {

    }

    @PacketHandler
    public void onStart(Room room, TDMData data, NetContext<ServerboundStartPacket> context) {
        BFClient client = context.client();
        if (client.id() != room.masterId()) {
            client.send(new ClientboundStartPacket().result(-2));
            return;
        }
        if (room.status() != RoomStatus.WAITING) {
            // Already started
            return;
        }
        int remain = context.packet().remain();
        if (remain != 0) {
            // Countdown tick or cancel (negative)
            room.broadcast(new ClientboundStartingPacket().remain(remain < 0 ? -1 : remain));
            return;
        }
        room.status(RoomStatus.PLAYING);
        room.broadcast(new ClientboundStartingPacket().remain(0), new ClientboundStartPacket().result(0));
        // Everyone waits for each other
        data.resetMatch();
        for (BFClient member : room.clients().toArray(new BFClient[0])) {
            data.registerPlayer(member.id(), TeamGameData.isRed(slotOf(member)), member.name());
            room.broadcast(new ClientboundLoadStartPacket().clientId(member.id()));
            member.send(new ClientboundRespawnTicketPacket().ticket(data.nextTicket(slotOf(member))));
        }
        context.manager().broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
        context.manager().logger().info("Room {0} ({1}) of {2} started with {3} player(s)", room.id(), room.title(), client,
            room.players());
    }

    @PacketHandler
    public void onRespawnTicket(Room room, TDMData data, NetContext<ServerboundRespawnTicketPacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        synchronized (data) {
            // The player asks for a ticket as soon as it respawns
            PlayerStats stats = data.stats(context.client().id());
            if (stats != null) {
                stats.alive(true);
            }
        }
        context.client().send(new ClientboundRespawnTicketPacket().ticket(data.nextTicket(slotOf(context.client()))));
    }

    @PacketHandler
    public void onTeamScore(Room room, TDMData data, NetContext<ServerboundTeamScorePacket> context) {
        context.client().send(new ClientboundTeamScorePacket().redScore(data.redScore()).blueScore(data.blueScore()));
    }

    @PacketHandler
    public void onBreakInto(Room room, TDMData data, NetContext<ServerboundBreakIntoPacket> context) {
        BFClient client = context.client();
        if (room.status() != RoomStatus.PLAYING || !data.running()) {
            client.send(new ClientboundBreakIntoPacket().result(-2));
            return;
        }
        if (!data.canJoinMidGame() || data.stats(client.id()) != null) {
            client.send(new ClientboundBreakIntoPacket().result(-1));
            return;
        }
        int slot = slotOf(client);
        data.registerPlayer(client.id(), TeamGameData.isRed(slot), client.name());

        // Everything the player missed since the match started
        List<IClientboundPacket> state = new ArrayList<>();
        state.add(new ClientboundBreakIntoPacket().result(0));
        state.add(new ClientboundRespawnTicketPacket().ticket(data.nextTicket(slot)));
        state.add(new ClientboundTeamScorePacket().redScore(data.redScore()).blueScore(data.blueScore()));
        for (PlayerStats stats : data.allStats()) {
            state.add(new ClientboundKillCountPacket().clientId(stats.clientId()).kill(stats.kill()));
            state.add(new ClientboundDeathCountPacket().clientId(stats.clientId()).death(stats.death()));
            state.add(new ClientboundAssistCountPacket().clientId(stats.clientId()).assist(stats.assist()).score(stats.score()));
        }
        data.destroyedBricks().forEach(brick -> state.add(new ClientboundDestroyedBrickPacket().brick(brick)));
        data.drops().forEach(item -> state.add(new ClientboundDropItemPacket().item(item)));
        data.doors().forEach(door -> state.add(new ClientboundChgDoorStatusPacket().door(door.id()).status((byte) (door.open() ? 1 : 0))));
        data.cannons().forEach(cannon -> state.add(new ClientboundGetCannonPacket().shooter(cannon.clientId()).cannon(cannon.object())));
        data.trains().forEach(train -> state.add(new ClientboundGetTrainPacket().shooter(train.clientId()).train(train.object())));
        int[] wanted = data.allStats().stream().filter(PlayerStats::wanted).mapToInt(PlayerStats::clientId).toArray();
        if (wanted.length > 0) {
            state.add(new ClientboundKnowWantedPacket().clientIds(wanted));
        }
        if (data.remainTime() >= 0) {
            state.add(new ClientboundTimerPacket().remainTime(data.remainTime()).playTime(data.playTime()));
        }
        client.send(state.toArray(IClientboundPacket[]::new));
        room.broadcast(new ClientboundLoadStartPacket().clientId(client.id()));
    }

    @PacketHandler
    public void onResultDone(Room room, TDMData data, NetContext<ServerboundResultDonePacket> context) {
        if (room.status() != RoomStatus.MATCH_END) {
            return;
        }
        BFClient client = context.client();
        client.attrSet(Room.ATTR_STATUS, ClientStatus.WAITING);
        room.broadcast(new ClientboundResultDonePacket().clientId(client.id()));
        if (data.resultDone(client.id())) {
            mode.resultsConfirmed(room);
        }
    }

    @PacketHandler
    public void onKillLog(Room room, TDMData data, NetContext<ServerboundKillLogPacket> context) {
        BFClient reporter = context.client();
        ServerboundKillLogPacket packet = context.packet();
        // The victim reports its own death
        if (room.status() != RoomStatus.PLAYING || packet.victimType() != 0 || packet.victim() != reporter.id()) {
            return;
        }
        List<IClientboundPacket> packets = new ArrayList<>();
        boolean goalReached;
        synchronized (data) {
            PlayerStats victim = data.stats(reporter.id());
            if (!data.running() || victim == null || !victim.alive()) {
                return;
            }
            victim.alive(false);
            victim.addDeath();
            victim.resetStreak();

            PlayerStats killer = packet.killerType() == 0 ? data.stats(packet.killer()) : null;
            boolean credited = killer != null && killer != victim && killer.red() != victim.red();

            packets.add(new ClientboundKillLogPacket().killerType(packet.killerType()).killer(packet.killer())
                    .victimType(packet.victimType()).victim(packet.victim()).weaponBy(packet.weaponBy()).hitpart(packet.hitpart()));
            if (victim.wanted()) {
                victim.wanted(false);
                packets.add(new ClientboundDeselectWantedPacket().clientId(victim.clientId()));
                if (credited) {
                    killer.addScore(TdmScoring.WANTED_BONUS);
                }
            }
            if (credited) {
                killer.addKill();
                killer.addScore(TdmScoring.KILL_SCORE + (packet.hitpart() == TdmScoring.HEAD_HITPART ? TdmScoring.HEADSHOT_SCORE : 0));
                data.addScore(killer.red());
                packets.add(new ClientboundKillCountPacket().clientId(killer.clientId()).kill(killer.kill()));
                if (data.wanted() && !killer.wanted() && killer.addStreak() >= TdmScoring.WANTED_STREAK) {
                    killer.wanted(true);
                    packets.add(new ClientboundSelectWantedPacket().clientId(killer.clientId()));
                }
            }
            packets.add(new ClientboundDeathCountPacket().clientId(victim.clientId()).death(victim.death()));
            if (credited) {
                for (DamageEntry entry : packet.damageLog()) {
                    PlayerStats attacker = data.stats(entry.clientId());
                    if (attacker == null || attacker == killer || attacker == victim || attacker.red() == victim.red()
                            || entry.damage() < TdmScoring.MIN_ASSIST_DAMAGE) {
                        continue;
                    }
                    attacker.addAssist();
                    attacker.addScore(TdmScoring.ASSIST_SCORE);
                    packets.add(scorePacket(attacker));
                }
                // The kill changed the score of the killer
                packets.add(scorePacket(killer));
            }
            packets.add(new ClientboundTeamScorePacket().redScore(data.redScore()).blueScore(data.blueScore()));
            goalReached = data.goal() > 0 && (data.redScore() >= data.goal() || data.blueScore() >= data.goal());
        }
        room.broadcast(packets.toArray(IClientboundPacket[]::new));
        if (goalReached) {
            mode.endMatch(context.manager(), room, data);
        }
    }

    @PacketHandler
    public void onInflictedDamage(Room room, TDMData data, NetContext<ServerboundInflictedDamagePacket> context) {
        long total = 0;
        for (DamageEntry entry : context.packet().damage()) {
            if (entry.damage() > 0) {
                total += entry.damage();
            }
        }
        synchronized (data) {
            PlayerStats stats = data.stats(context.client().id());
            if (stats != null && data.running()) {
                stats.addDamage((int) Math.min(total, Integer.MAX_VALUE));
            }
        }
    }

    @PacketHandler
    public void onSerialBonus(Room room, TDMData data, NetContext<ServerboundSerialBonusPacket> context) {
        int bonus = context.packet().bonus();
        if (room.status() != RoomStatus.PLAYING || bonus <= 0 || bonus > TdmScoring.MAX_SERIAL_BONUS) {
            return;
        }
        IClientboundPacket update;
        synchronized (data) {
            PlayerStats stats = data.stats(context.client().id());
            if (stats == null || !data.running()) {
                return;
            }
            stats.addScore(bonus);
            update = scorePacket(stats);
        }
        room.broadcast(update);
    }

    @PacketHandler
    public void onStackPoint(Room room, TDMData data, NetContext<ServerboundStackPointPacket> context) {
        synchronized (data) {
            PlayerStats stats = data.stats(context.client().id());
            if (stats != null && data.running()) {
                stats.addPlayMinute();
            }
        }
    }

    @PacketHandler
    public void onTimer(Room room, TDMData data, NetContext<ServerboundTimerPacket> context) {
        ServerboundTimerPacket packet = context.packet();
        // The time is the one of the master, the others adjust to it
        if (room.status() != RoomStatus.PLAYING || context.client().id() != room.masterId() || packet.remainTime() < 0
                || packet.playTime() < 0) {
            return;
        }
        data.updateTime(packet.remainTime(), packet.playTime());
        room.broadcast(context.client(), new ClientboundTimerPacket().remainTime(packet.remainTime()).playTime(packet.playTime()));
        if (packet.remainTime() == 0 && data.timeLimit() > 0) {
            mode.endMatch(context.manager(), room, data);
        }
    }

    @PacketHandler
    public void onDestroyBrick(Room room, TDMData data, NetContext<ServerboundDestroyBrickPacket> context) {
        if (room.status() != RoomStatus.PLAYING || data.stats(context.client().id()) == null) {
            return;
        }
        int brick = context.packet().brick();
        // Everyone gets it, even the one who destroyed the brick
        if (data.destroyBrick(brick)) {
            room.broadcast(new ClientboundDestroyBrickPacket().brick(brick));
        }
    }

    private static ClientboundAssistCountPacket scorePacket(PlayerStats stats) {
        return new ClientboundAssistCountPacket().clientId(stats.clientId()).assist(stats.assist()).score(stats.score());
    }

    @PacketHandler
    public void onSlotLock(final Room room, final TDMData data, final NetContext<ServerboundSlotLockPacket> context) {
        BFClient client = context.client();
        if (client.id() != room.masterId() || room.status() != RoomStatus.WAITING) {
            return;
        }
        int slot = context.packet().slot();
        boolean locked = context.packet().lck() != 0;
        if (!room.lockSlot(slot, locked)) {
            return;
        }
        room.broadcast(new ClientboundSlotLockPacket().index((byte) slot).slotLocked(locked));
    }

    @PacketHandler
    public void onRadio(final Room room, final TDMData data, final NetContext<ServerboundRadioMsgPacket> context) {
        BFClient client = context.client();
        int category = context.packet().category();
        int message = context.packet().msg();
        if (category < 0 || category > 1000 || message < 0 || message > 1000) {
            return;
        }
        sendToTeam(room, client, new ClientboundRadioMsgPacket().clientId(client.id()).category(category).message(message));
    }

    @PacketHandler
    public void onLevelupEvent(final Room room, final TDMData data, final NetContext<ServerboundLevelupEventPacket> context) {
        BFClient client = context.client();
        UserProfile profile = client.attr(ClientListener.ATTR_PROFILE, UserProfile.class);
        if (profile == null) {
            return;
        }
        room.broadcast(new ClientboundLevelupEventPacket().eventType((byte)0).clientId(client.id()).xp(profile.xp()));
    }

    private static void sendToTeam(final Room room, final BFClient client, final IClientboundPacket packet) {
        boolean teams = room.gameData() instanceof TeamGameData;
        boolean red = TeamGameData.isRed(slotOf(client));
        for (BFClient member : room.clients().toArray(new BFClient[0])) {
            if (!teams || TeamGameData.isRed(slotOf(member)) == red) {
                member.send(packet);
            }
        }
    }

    private static int slotOf(BFClient client) {
        Integer slot = client.attr(Room.ATTR_SLOT, Integer.class);
        return slot == null ? 0 : slot;
    }

    @PacketHandler
    public void onLoadComplete(Room room, TDMData data, NetContext<ServerboundLoadCompletePacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        room.broadcast(new ClientboundLoadCompletePacket().clientId(context.client().id()));
    }

    @PacketHandler
    public void onPeerToPeerComplete(Room room, TDMData data, NetContext<ServerboundPToPCompletePacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        room.broadcast(new ClientboundPToPCompletePacket().clientId(context.client().id()));
    }

    @PacketHandler
    public void onMatchCountdown(Room room, TDMData data, NetContext<ServerboundMatchCountdownPacket> context) {
        if (room.status() != RoomStatus.PLAYING || context.client().id() != room.masterId()) {
            return;
        }
        room.broadcast(new ClientboundMatchCountdownPacket().count(context.packet().count()));
    }

    @PacketHandler
    public void onResumeRoom(Room room, TDMData data, NetContext<ServerboundResumeRoomPacket> context) {
        if (context.client().id() != room.masterId()) {
            return;
        }
        RoomStatus status = RoomStatus.byId(context.packet().nextStatus());
        if (status == null || status == room.status()) {
            return;
        }
        room.status(status);
        context.manager().broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
    }

}
