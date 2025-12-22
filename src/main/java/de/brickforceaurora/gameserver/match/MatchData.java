package de.brickforceaurora.gameserver.match;

import java.util.*;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.gamemode.ZombieStep;
import de.brickforceaurora.gameserver.net.BrickManStatus;
import de.brickforceaurora.gameserver.net.ChannelReference;
import de.brickforceaurora.gameserver.net.ClientReference;
import de.brickforceaurora.gameserver.net.ClientStatus;
import de.brickforceaurora.gameserver.room.Room;
import de.brickforceaurora.gameserver.room.RoomStatus;
import de.brickforceaurora.gameserver.room.RoomType;
import de.brickforceaurora.gameserver.util.Utils;

public final class MatchData {

    /* ===== Timers & scores ===== */

    public int countdownTime;
    public int remainTime;
    public int playTime;
    public int blueScore;
    public int redScore;
    public ClientReference highestKillsClient;
    public int lobbyCountdownTime;
    public int masterSeq;
    public boolean isBalance;
    public boolean roomCreated;
    public int lastKillLogId = -1;

    /* ===== Core collections ===== */

    public List<ClientReference> clientList;
    public List<SlotData> slots;
    public List<SlotData> blueSlots;
    public List<SlotData> redSlots;

    public List<Integer> destroyedBricks;
    public Map<Integer, Integer> usedCannons;
    public Map<Integer, Integer> usedTrains;
    public List<KillLogEntry> killLog;

    public ChannelReference channel;
    public Room room;

    /* ===== Kill counts ===== */

    public int redKillCount;
    public int blueKillCount;

    /* ===== Build ===== */

    //public UserMap cachedMap;
    //public UserMapInfo cachedUMI;
    public boolean mapCached;

    /* ===== BND ===== */

    public boolean isBuildPhase;
    public boolean useBuildGun;
    public int repeat;
    public int currentRound;
    public int buildPhaseTime;
    public int battlePhaseTime;

    /* ===== Zombie ===== */

    public List<Integer> humanPlayers;
    public List<Integer> zombiePlayers;
    public List<Integer> killedPlayers;
    public List<Integer> infectedPlayers;

    public boolean roundInit;
    public int zombieCountdown;
    public int zombieRounds;
    public int zombieCurrentRound;
    public int zombieRoundsLeft;
    public int zombieTimePerRound;
    public double zombieDeltaTimer;
    public ZombieStep zombieStatus;

    /* ===== Defusion ===== */

    public List<Integer> deadRedPlayers = new ArrayList<>();
    public List<Integer> deadBluePlayers = new ArrayList<>();

    /* ============================================================ */

    public MatchData() {
        countdownTime = 0;
        remainTime = 0;
        playTime = 0;
        blueScore = 0;
        redScore = 0;
        highestKillsClient = null;
        lobbyCountdownTime = 0;
        masterSeq = 0;
        isBalance = false;
        roomCreated = false;

        clientList = new ArrayList<>();
        slots = new ArrayList<>();
        killLog = new ArrayList<>();
        destroyedBricks = new ArrayList<>();
        usedCannons = new HashMap<>();
        usedTrains = new HashMap<>();

        for (int i = 0; i < 16; i++)
            slots.add(new SlotData(i));

        List<List<SlotData>> split = Utils.SplitList(slots, 8);
        redSlots = split.get(0);
        blueSlots = split.get(1);

        for (SlotData s : redSlots)
            s.isRed = true;

        isBuildPhase = true;
        repeat = 0;
        currentRound = 1;
        buildPhaseTime = 0;
        battlePhaseTime = 0;

        redKillCount = 0;
        blueKillCount = 0;

        room = new Room(false, 0, "", RoomType.TEAM_MATCH,
                RoomStatus.WAITING,
                0, 0, 0, "", 0, 0, 0,
                0, 0, 0, 0,
                false, false, false, 0, 0);

        //cachedMap = new UserMap();
        mapCached = false;

        roundInit = true;
        humanPlayers = new ArrayList<>();
        zombiePlayers = new ArrayList<>();
        killedPlayers = new ArrayList<>();
        infectedPlayers = new ArrayList<>();

        zombieCountdown = 0;
        zombieRounds = 0;
        zombieTimePerRound = 0;
        zombieRoundsLeft = 0;
        zombieDeltaTimer = 0;
        zombieCurrentRound = 1;
    }

    /* ============================================================ */

    public void Reset() {
        blueScore = 0;
        redScore = 0;
        highestKillsClient = null;

        destroyedBricks.clear();
        usedCannons.clear();
        usedTrains.clear();
        killLog.clear();

        redKillCount = 0;
        blueKillCount = 0;

        isBuildPhase = true;
        repeat = 0;
        currentRound = 1;
        buildPhaseTime = 0;
        battlePhaseTime = 0;

        room.status = RoomStatus.WAITING;

        for (ClientReference c : clientList) {
            c.clientStatus = ClientStatus.ROOM;
            c.deaths = 0;
            c.kills = 0;
            c.assists = 0;
            c.isZombie = false;
            c.score = 0;
        }

        roundInit = true;
        humanPlayers.clear();
        zombiePlayers.clear();
        killedPlayers.clear();
        infectedPlayers.clear();

        zombieCountdown = 0;
        zombieRounds = 0;
        zombieTimePerRound = 0;
        zombieRoundsLeft = 0;
        zombieDeltaTimer = 0;
        zombieCurrentRound = 1;

        deadRedPlayers.clear();
        deadBluePlayers.clear();
    }

    /* ============================================================ */

    public void Shutdown() {
        for (ClientReference c : clientList) {
            c.matchData = null;
            c.DetachSlot();
            c.clientStatus = ClientStatus.LOBBY;
            c.status = BrickManStatus.PLAYER_WAITING;
            c.deaths = 0;
            c.kills = 0;
            c.assists = 0;
            c.score = 0;
            room.curPlayer = clientList.size();
        }
        Reset();
    }

    /* ============================================================ */

    /*public void EndMatch() {
        switch (room.type) {
            case TEAM_MATCH -> GameServer.getInstance().handleTeamMatchEnd(this);
            case INDIVIDUAL -> GameServer.getInstance().handleIndividualMatchEnd(this);
            case CAPTURE_THE_FLAG -> CTF.handleCTFMatchEnd(this);
            case BND -> BND.handleBNDMatchEnd(this);
            case ZOMBIE -> Zombie.handleZombieMatchEnd(this);
            case EXPLOSION -> Defusion.handleMatchEnd(this);
            case ESCAPE, MISSION -> DefenseGamemode.handleMatchEnd(this);
            case BUNGEE -> Freefall.handleMatchEnd(this);
            default -> GameServer.getInstance().handleIndividualMatchEnd(this);
        }
    }*/

    /* ============================================================ */

    public void AddClient(ClientReference client) {
        client.matchData = this;
        client.AssignSlot(GetNextFreeSlot());
        client.clientStatus = ClientStatus.ROOM;
        clientList.add(client);
        room.curPlayer = clientList.size();
    }

    public void RemoveClient(ClientReference client) {
        client.matchData = null;
        client.DetachSlot();
        client.clientStatus = ClientStatus.LOBBY;
        client.status = BrickManStatus.PLAYER_WAITING;
        client.deaths = 0;
        client.kills = 0;
        client.assists = 0;
        client.score = 0;
        clientList.remove(client);
        room.curPlayer = clientList.size();
    }

    /* ============================================================ */

    public SlotData GetNextFreeSlot() {
        if (room.type == RoomType.INDIVIDUAL || room.type == RoomType.ZOMBIE) {
            for (SlotData s : slots)
                if (!s.isUsed && !s.isLocked)
                    return s;
            return null;
        }

        if (room.type == RoomType.BUNGEE || room.type == RoomType.MISSION) {
            int used = 0;
            for (SlotData s : slots)
                if (s.isUsed) used++;
            if (used >= 8) return null;
        }

        int red = 0, blue = 0;
        for (SlotData s : redSlots) if (s.isUsed) red++;
        for (SlotData s : blueSlots) if (s.isUsed) blue++;

        List<SlotData> first = blue >= red ? redSlots : blueSlots;
        List<SlotData> second = blue >= red ? blueSlots : redSlots;

        for (SlotData s : first)
            if (!s.isUsed && !s.isLocked) return s;
        for (SlotData s : second)
            if (!s.isUsed && !s.isLocked) return s;

        return null;
    }

    public SlotData FindSlotByClient(ClientReference client) {
        for (SlotData s : slots)
            if (s.client == client)
                return s;

        GameServerApp.logger().debug("FindSlotByClient: Could not find SlotData for client {0}", client.GetIdentifier());
        return null;
    }
}
