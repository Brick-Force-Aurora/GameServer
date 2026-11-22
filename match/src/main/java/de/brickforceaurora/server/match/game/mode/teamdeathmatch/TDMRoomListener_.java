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
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundAssistCountPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundBreakIntoPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundChgDoorStatusPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDeathCountPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDeselectWantedPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDestroyBrickPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDestroyedBrickPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundDropItemPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundGetCannonPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundGetTrainPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundKillCountPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundKillLogPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundKnowWantedPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundLevelupEventPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundLoadCompletePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundLoadStartPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMatchCountdownPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPToPCompletePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundRadioMsgPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundRespawnTicketPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundResultDonePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundSelectWantedPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundSlotLockPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundStartPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundStartingPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundTeamScorePacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundTimerPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundUpdateRoomPacket;
import de.brickforceaurora.server.net.protocol.data.ClientStatus;
import de.brickforceaurora.server.net.protocol.data.DamageEntry;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundAcceptApplicantPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundBreakIntoPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundDestroyBrickPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundInflictedDamagePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundKillLogPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundLevelupEventPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundLoadCompletePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundMatchCountdownPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundPToPCompletePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRadioMsgPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRespawnTicketPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundResultDonePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundResumeRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundSlotLockPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundStackPointPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundStartPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundTeamScorePacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundTimerPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@GameTarget(TeamDeathMatchMode.class)
public class TDMRoomListener_ implements IGameNetListener<TDMData> {

    private final TeamDeathMatchMode mode;

    public TDMRoomListener_(final TeamDeathMatchMode mode) {
        this.mode = mode;
    }

    @PacketHandler
    public void handleMyExamplePacket(final Room room, final TDMData data, final NetContext<ServerboundAcceptApplicantPacket> context) {

    }

    @PacketHandler
    public void onStart(final Room room, final TDMData data, final NetContext<ServerboundStartPacket> context) {
        final BFClient client = context.client();
        if (client.id() != room.masterId()) {
            client.send(new ClientboundStartPacket().result(-2));
            return;
        }
        if (room.status() != RoomStatus.WAITING) {
            // Already started
            return;
        }
        final int remain = context.packet().remain();
        if (remain != 0) {
            // Countdown tick or cancel (negative)
            room.broadcast(new ClientboundStartingPacket().remain(remain < 0 ? -1 : remain));
            return;
        }
        room.status(RoomStatus.PLAYING);
        room.broadcast(new ClientboundStartingPacket().remain(0), new ClientboundStartPacket().result(0));
        // Everyone waits for each other
        data.resetMatch();
        for (final BFClient member : room.clients().toArray(new BFClient[0])) {
            data.registerPlayer(member.id(), TeamGameData.isRed(slotOf(member)), member.name());
            room.broadcast(new ClientboundLoadStartPacket().clientId(member.id()));
            member.send(new ClientboundRespawnTicketPacket().ticket(data.nextTicket(slotOf(member))));
        }
        context.manager().broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
        context.manager().logger().info("Room {0} ({1}) of {2} started with {3} player(s)", room.id(), room.title(), client,
            room.players());
    }

    @PacketHandler
    public void onRespawnTicket(final Room room, final TDMData data, final NetContext<ServerboundRespawnTicketPacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        synchronized (data) {
            // The player asks for a ticket as soon as it respawns
            final PlayerStats stats = data.stats(context.client().id());
            if (stats != null) {
                stats.alive(true);
            }
        }
        context.client().send(new ClientboundRespawnTicketPacket().ticket(data.nextTicket(slotOf(context.client()))));
    }

    @PacketHandler
    public void onTeamScore(final Room room, final TDMData data, final NetContext<ServerboundTeamScorePacket> context) {
        context.client().send(new ClientboundTeamScorePacket().redScore(data.redScore()).blueScore(data.blueScore()));
    }

    @PacketHandler
    public void onBreakInto(final Room room, final TDMData data, final NetContext<ServerboundBreakIntoPacket> context) {
        final BFClient client = context.client();
        if (room.status() != RoomStatus.PLAYING || !data.running()) {
            client.send(new ClientboundBreakIntoPacket().result(-2));
            return;
        }
        if (!data.canJoinMidGame() || data.stats(client.id()) != null) {
            client.send(new ClientboundBreakIntoPacket().result(-1));
            return;
        }
        final int slot = slotOf(client);
        data.registerPlayer(client.id(), TeamGameData.isRed(slot), client.name());

        // Everything the player missed since the match started
        final List<IClientboundPacket> state = new ArrayList<>();
        state.add(new ClientboundBreakIntoPacket().result(0));
        state.add(new ClientboundRespawnTicketPacket().ticket(data.nextTicket(slot)));
        state.add(new ClientboundTeamScorePacket().redScore(data.redScore()).blueScore(data.blueScore()));
        for (final PlayerStats stats : data.allStats()) {
            state.add(new ClientboundKillCountPacket().clientId(stats.clientId()).kill(stats.kill()));
            state.add(new ClientboundDeathCountPacket().clientId(stats.clientId()).death(stats.death()));
            state.add(new ClientboundAssistCountPacket().clientId(stats.clientId()).assist(stats.assist()).score(stats.score()));
        }
        data.destroyedBricks().forEach(brick -> state.add(new ClientboundDestroyedBrickPacket().brick(brick)));
        data.drops().forEach(item -> state.add(new ClientboundDropItemPacket().item(item)));
        data.doors().forEach(door -> state.add(new ClientboundChgDoorStatusPacket().door(door.id()).status((byte) (door.open() ? 1 : 0))));
        data.cannons().forEach(cannon -> state.add(new ClientboundGetCannonPacket().shooter(cannon.clientId()).cannon(cannon.object())));
        data.trains().forEach(train -> state.add(new ClientboundGetTrainPacket().shooter(train.clientId()).train(train.object())));
        final int[] wanted = data.allStats().stream().filter(PlayerStats::wanted).mapToInt(PlayerStats::clientId).toArray();
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
    public void onResultDone(final Room room, final TDMData data, final NetContext<ServerboundResultDonePacket> context) {
        if (room.status() != RoomStatus.MATCH_END) {
            return;
        }
        final BFClient client = context.client();
        client.attrSet(Room.ATTR_STATUS, ClientStatus.WAITING);
        room.broadcast(new ClientboundResultDonePacket().clientId(client.id()));
        if (data.resultDone(client.id())) {
            mode.resultsConfirmed(room);
        }
    }

    @PacketHandler
    public void onKillLog(final Room room, final TDMData data, final NetContext<ServerboundKillLogPacket> context) {
        final BFClient reporter = context.client();
        final ServerboundKillLogPacket packet = context.packet();
        // The victim reports its own death
        if (room.status() != RoomStatus.PLAYING || packet.victimType() != 0 || packet.victim() != reporter.id()) {
            return;
        }
        final List<IClientboundPacket> packets = new ArrayList<>();
        boolean goalReached;
        synchronized (data) {
            final PlayerStats victim = data.stats(reporter.id());
            if (!data.running() || victim == null || !victim.alive()) {
                return;
            }
            victim.alive(false);
            victim.addDeath();
            victim.resetStreak();

            final PlayerStats killer = packet.killerType() == 0 ? data.stats(packet.killer()) : null;
            final boolean credited = killer != null && killer != victim && killer.red() != victim.red();

            packets.add(new ClientboundKillLogPacket().killerType(packet.killerType()).killer(packet.killer())
                .victimType(packet.victimType()).victim(packet.victim()).weaponBy(packet.weaponBy()).hitpart(packet.hitpart()));
            if (victim.wanted()) {
                victim.wanted(false);
                packets.add(new ClientboundDeselectWantedPacket().clientId(victim.clientId()));
            }
            if (credited) {
                killer.addKill();
                data.addScore(killer.red());
                packets.add(new ClientboundKillCountPacket().clientId(killer.clientId()).kill(killer.kill()));
                if (data.wanted() && !killer.wanted() && killer.addStreak() >= TDMScoring.WANTED_STREAK) {
                    killer.wanted(true);
                    packets.add(new ClientboundSelectWantedPacket().clientId(killer.clientId()));
                }
            }
            packets.add(new ClientboundDeathCountPacket().clientId(victim.clientId()).death(victim.death()));
            if (credited) {
                for (final DamageEntry entry : packet.damageLog()) {
                    final PlayerStats attacker = data.stats(entry.clientId());
                    if (attacker == null || attacker == killer || attacker == victim || attacker.red() == victim.red()
                        || entry.damage() < TDMScoring.MIN_ASSIST_DAMAGE) {
                        continue;
                    }
                    attacker.addAssist();
                    packets.add(scorePacket(attacker));
                }
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
    public void onInflictedDamage(final Room room, final TDMData data, final NetContext<ServerboundInflictedDamagePacket> context) {
        IClientboundPacket update;
        synchronized (data) {
            final PlayerStats attacker = data.stats(context.client().id());
            if (attacker == null || !data.running()) {
                return;
            }
            long total = 0;
            for (final DamageEntry entry : context.packet().damage()) {
                final PlayerStats victim = data.stats(entry.clientId());
                // Only the damage to the enemy team counts
                if (entry.damage() > 0 && victim != null && victim.red() != attacker.red()) {
                    total += entry.damage();
                }
            }
            if (total <= 0) {
                return;
            }
            final int scoreBefore = TDMScoring.score(attacker.damage());
            attacker.addDamage((int) Math.min(total, Integer.MAX_VALUE));
            attacker.addScore(TDMScoring.score(attacker.damage()) - scoreBefore);
            update = scorePacket(attacker);
        }
        room.broadcast(update);
    }

    @PacketHandler
    public void onStackPoint(final Room room, final TDMData data, final NetContext<ServerboundStackPointPacket> context) {
        synchronized (data) {
            final PlayerStats stats = data.stats(context.client().id());
            if (stats != null && data.running()) {
                stats.addPlayMinute();
            }
        }
    }

    @PacketHandler
    public void onTimer(final Room room, final TDMData data, final NetContext<ServerboundTimerPacket> context) {
        final ServerboundTimerPacket packet = context.packet();
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
    public void onDestroyBrick(final Room room, final TDMData data, final NetContext<ServerboundDestroyBrickPacket> context) {
        if (room.status() != RoomStatus.PLAYING || data.stats(context.client().id()) == null) {
            return;
        }
        final int brick = context.packet().brick();
        // Everyone gets it, even the one who destroyed the brick
        if (data.destroyBrick(brick)) {
            room.broadcast(new ClientboundDestroyBrickPacket().brick(brick));
        }
    }

    private static ClientboundAssistCountPacket scorePacket(final PlayerStats stats) {
        return new ClientboundAssistCountPacket().clientId(stats.clientId()).assist(stats.assist()).score(stats.score());
    }

    @PacketHandler
    public void onSlotLock(final Room room, final TDMData data, final NetContext<ServerboundSlotLockPacket> context) {
        final BFClient client = context.client();
        if (client.id() != room.masterId() || room.status() != RoomStatus.WAITING) {
            return;
        }
        final int slot = context.packet().slot();
        final boolean locked = context.packet().lck() != 0;
        if (!room.lockSlot(slot, locked)) {
            return;
        }
        room.broadcast(new ClientboundSlotLockPacket().index((byte) slot).slotLocked(locked));
    }

    @PacketHandler
    public void onRadio(final Room room, final TDMData data, final NetContext<ServerboundRadioMsgPacket> context) {
        final BFClient client = context.client();
        final int category = context.packet().category();
        final int message = context.packet().msg();
        if (category < 0 || category > 1000 || message < 0 || message > 1000) {
            return;
        }
        sendToTeam(room, client, new ClientboundRadioMsgPacket().clientId(client.id()).category(category).message(message));
    }

    @PacketHandler
    public void onLevelupEvent(final Room room, final TDMData data, final NetContext<ServerboundLevelupEventPacket> context) {
        final BFClient client = context.client();
        final UserProfile profile = client.attr(ClientListener.ATTR_PROFILE, UserProfile.class);
        if (profile == null) {
            return;
        }
        room.broadcast(new ClientboundLevelupEventPacket().eventType((byte) 0).clientId(client.id()).xp(profile.xp()));
    }

    private static void sendToTeam(final Room room, final BFClient client, final IClientboundPacket packet) {
        final boolean teams = room.gameData() instanceof TeamGameData;
        final boolean red = TeamGameData.isRed(slotOf(client));
        for (final BFClient member : room.clients().toArray(new BFClient[0])) {
            if (!teams || TeamGameData.isRed(slotOf(member)) == red) {
                member.send(packet);
            }
        }
    }

    private static int slotOf(final BFClient client) {
        final Integer slot = client.attr(Room.ATTR_SLOT, Integer.class);
        return slot == null ? 0 : slot;
    }

    @PacketHandler
    public void onLoadComplete(final Room room, final TDMData data, final NetContext<ServerboundLoadCompletePacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        room.broadcast(new ClientboundLoadCompletePacket().clientId(context.client().id()));
    }

    @PacketHandler
    public void onPeerToPeerComplete(final Room room, final TDMData data, final NetContext<ServerboundPToPCompletePacket> context) {
        if (room.status() != RoomStatus.PLAYING) {
            return;
        }
        room.broadcast(new ClientboundPToPCompletePacket().clientId(context.client().id()));
    }

    @PacketHandler
    public void onMatchCountdown(final Room room, final TDMData data, final NetContext<ServerboundMatchCountdownPacket> context) {
        if (room.status() != RoomStatus.PLAYING || context.client().id() != room.masterId()) {
            return;
        }
        room.broadcast(new ClientboundMatchCountdownPacket().count(context.packet().count()));
    }

    @PacketHandler
    public void onResumeRoom(final Room room, final TDMData data, final NetContext<ServerboundResumeRoomPacket> context) {
        if (context.client().id() != room.masterId()) {
            return;
        }
        final RoomStatus status = RoomStatus.byId(context.packet().nextStatus());
        if (status == null || status == room.status()) {
            return;
        }
        room.status(status);
        context.manager().broadcast(new ClientboundUpdateRoomPacket().roomInfo(room));
    }

}
