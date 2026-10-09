package de.brickforceaurora.server.match.room;

import de.brickforceaurora.server.match.gamemode.GameData;
import de.brickforceaurora.server.match.gamemode.GameMode;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.protocol.data.CountryFilter;
import de.brickforceaurora.server.net.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;

public final class Room implements IRoomInfo {
    
    public static final String ATTR_ROOM = "MatchRoom";

    private final int id;
    private volatile String title;
    private volatile String password;
    private volatile RoomStatus status;

    private final ObjectList<BFClient> clients = ObjectLists.synchronize(new ObjectArrayList<>());

    private volatile int maxPlayers = 16;

    private volatile GameMode<?> mode;
    private volatile GameData gameData;

    public Room(int id) {
        this.id = id;
    }

    public GameMode<?> mode() {
        return mode;
    }

    public Room mode(GameMode<?> mode) {
        if (this.mode == mode) {
            return this;
        }
        this.mode = mode;
        this.gameData = mode.createGameDataFor(this);
        return this;
    }

    public GameData gameData() {
        return gameData;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public RoomType type() {
        return mode.roomType();
    }

    @Override
    public String title() {
        return title;
    }

    public Room title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public boolean passwordLocked() {
        return password != null;
    }

    public Room password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public RoomStatus status() {
        return status;
    }

    public Room status(RoomStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public int players() {
        return clients.size();
    }

    @Override
    public int maxPlayers() {
        return maxPlayers;
    }

    public Room maxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    @Override
    public int mapId() {
        // TODO: Map
        return 0;
    }

    @Override
    public String mapAlias() {
        // TODO: Map
        return null;
    }

    @Override
    public int goal() {
        // TODO: Goal
        return 0;
    }

    @Override
    public int timeLimit() {
        // TODO: Time limit
        return 0;
    }

    @Override
    public int weaponOption() {
        // TODO: Weapon Options - ENUM possible?
        return 0;
    }

    @Override
    public int ping() {
        // TODO: HOW????
        return 0;
    }

    @Override
    public int blueScore() {
        // TODO: First team score
        return 0;
    }

    @Override
    public int redScore() {
        // TODO: Second team score
        return 0;
    }

    @Override
    public CountryFilter countryFilter() {
        // TODO: Should we always use EU filter or always NONE?
        return CountryFilter.NONE;
    }

    @Override
    public boolean allowsLateJoining() {
        // TODO: Late joining
        return false;
    }

    @Override
    public boolean weaponDropEnabled() {
        // TODO: Weapon drops
        return false;
    }

    @Override
    public boolean wantedEnabled() {
        // TODO: Wanted sub gamemode
        return false;
    }

    @Override
    public int squad() {
        // TODO: What is this?
        return 0;
    }

    @Override
    public int squadCounter() {
        // TODO: What is this?
        return 0;
    }

    public boolean[] getSlotLocksByMaxPlayers() {
        boolean is8SlotLayout = (type() == RoomType.BUNGEE || type() == RoomType.MISSION);
        int totalSlots = is8SlotLayout ? 8 : 16;

        boolean[] slotLocks = new boolean[totalSlots];
        boolean isTeamMode = !(type() == RoomType.INDIVIDUAL || type() == RoomType.ZOMBIE);

        // SPECIAL CASE: Deathmatch / Zombie → lock bottom-up
        if (!isTeamMode) {
            for (int i = totalSlots - 1; i >= maxPlayers(); i--) {
                slotLocks[i] = true;
            }
            return slotLocks;
        }

        // TEAM MODE (8-slot or 16-slot)
        int redIndex = is8SlotLayout ? 3 : 7;
        int blueIndex = is8SlotLayout ? 7 : 15;

        // Normal team-mode locking (alternating)
        for (int i = totalSlots - 1; i >= maxPlayers(); i--) {
            boolean odd = (i % 2 != 0);

            if (odd) { // RED slot
                if (redIndex >= 0) {
                    slotLocks[redIndex] = true;
                }
                redIndex--;
            } else { // BLUE slot
                if (blueIndex >= 0) {
                    slotLocks[blueIndex] = true;
                }
                blueIndex--;
            }
        }

        return slotLocks;
    }

}
