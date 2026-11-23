package de.brickforceaurora.server.match.room;

import de.brickforceaurora.server.match.game.GameData;
import de.brickforceaurora.server.match.game.GameMode;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.CountryFilter;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomStatus;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomType;
import de.brickforceaurora.server.net.tcp.protocol.data.api.IRoomInfo;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;

public final class Room implements IRoomInfo {

    public static final String ATTR_ROOM = "MatchRoom";
    public static final String ATTR_SLOT = "MatchSlot";
    public static final String ATTR_STATUS = "MatchStatus";

    private final boolean[] manualSlotLocks = new boolean[16];

    private final int id;
    private volatile String title;
    private volatile String password;
    private volatile RoomStatus status;
    private volatile int masterId;

    private final ObjectList<BFClient> clients = ObjectLists.synchronize(new ObjectArrayList<>());

    private volatile int maxPlayers = 16;

    private volatile GameMode<?> mode;
    private volatile GameData gameData;

    public Room(final int id) {
        this.id = id;
    }

    public GameMode<?> mode() {
        return mode;
    }

    public Room mode(final GameMode<?> mode) {
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

    public Room title(final String title) {
        this.title = title;
        return this;
    }

    public String password() {
        return password;
    }

    public Room password(final String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean passwordLocked() {
        return password != null;
    }

    @Override
    public RoomStatus status() {
        return status;
    }

    public Room status(final RoomStatus status) {
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

    public Room maxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public int masterId() {
        return masterId;
    }

    public Room masterId(final int masterId) {
        this.masterId = masterId;
        return this;
    }

    @Override
    public int mapId() {
        return gameData().mapId();
    }

    @Override
    public String mapAlias() {
        return gameData().mapAlias();
    }

    @Override
    public int goal() {
        return gameData().goal();
    }

    @Override
    public int timeLimit() {
        return gameData().timeLimit();
    }

    @Override
    public int weaponOption() {
        // TODO: Weapon Options - ENUM possible?
        return gameData().weaponOption();
    }

    @Override
    public int ping() {
        // TODO: HOW????
        return 0;
    }

    @Override
    public int blueScore() {
        return gameData().blueScore();
    }

    @Override
    public int redScore() {
        return gameData().redScore();
    }

    @Override
    public CountryFilter countryFilter() {
        // TODO: Should we always use EU filter or always NONE?
        return CountryFilter.NONE;
    }

    @Override
    public boolean allowsLateJoining() {
        return gameData().canJoinMidGame();
    }

    @Override
    public boolean weaponDropEnabled() {
        return gameData().drop();
    }

    @Override
    public boolean wantedEnabled() {
        return gameData().wanted();
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

    public boolean lockSlot(final int slot, final boolean locked) {
        if (slot < 0 || slot >= manualSlotLocks.length || (locked && slotUsed(slot))) {
            return false;
        }
        manualSlotLocks[slot] = locked;
        return true;
    }

    public boolean slotUsed(final int slot) {
        for (final BFClient client : clients.toArray(new BFClient[0])) {
            final Integer used = client.attr(ATTR_SLOT, Integer.class);
            if (used != null && used == slot) {
                return true;
            }
        }
        return false;
    }

    public BFClient clientById(final int clientId) {
        for (final BFClient client : clients.toArray(new BFClient[0])) {
            if (client.id() == clientId) {
                return client;
            }
        }
        return null;
    }

    public boolean[] getSlotLocksByMaxPlayers() {
        final boolean[] slotLocks = layoutSlotLocks();
        for (int slot = 0; slot < slotLocks.length && slot < manualSlotLocks.length; slot++) {
            slotLocks[slot] |= manualSlotLocks[slot];
        }
        return slotLocks;
    }

    private boolean[] layoutSlotLocks() {
        final boolean is8SlotLayout = type() == RoomType.BUNGEE || type() == RoomType.MISSION;
        final int totalSlots = is8SlotLayout ? 8 : 16;

        final boolean[] slotLocks = new boolean[totalSlots];
        final boolean isTeamMode = ((type() != RoomType.INDIVIDUAL) && (type() != RoomType.ZOMBIE));

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
            final boolean odd = i % 2 != 0;

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

    public int freeSlot() {
        return freeSlot(0, Integer.MAX_VALUE);
    }

    public int freeSlot(final int from, final int to) {
        final boolean[] locks = getSlotLocksByMaxPlayers();
        final boolean[] used = new boolean[locks.length];
        for (final BFClient client : clients.toArray(new BFClient[0])) {
            final Integer slot = client.attr(ATTR_SLOT, Integer.class);
            if (slot != null && slot >= 0 && slot < used.length) {
                used[slot] = true;
            }
        }
        for (int slot = Math.max(from, 0); slot < locks.length && slot < to; slot++) {
            if (!locks[slot] && !used[slot]) {
                return slot;
            }
        }
        return -1;
    }

    public synchronized boolean moveToSlot(final BFClient client, final int slot) {
        if (slot < 0 || freeSlot(slot, slot + 1) != slot) {
            return false;
        }
        client.attrSet(ATTR_SLOT, slot);
        return true;
    }

    public void broadcast(final BFClient exclusive, final IClientboundPacket... packets) {
        clients.stream().filter(cl -> cl != exclusive).forEach(client -> client.send(packets));
    }

    public void broadcast(final IClientboundPacket... packets) {
        clients.forEach(client -> client.send(packets));
    }

    public ObjectList<BFClient> clients() {
        return clients;
    }

}
