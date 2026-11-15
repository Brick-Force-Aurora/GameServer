package de.brickforceaurora.server.match.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.brickforceaurora.server.net.protocol.data.Door;
import de.brickforceaurora.server.net.protocol.data.DroppedItem;
import de.brickforceaurora.server.net.protocol.data.Occupation;

public abstract class MatchGameData extends GameData {

    private final List<PlayerStats> stats = new ArrayList<>();
    private final List<DroppedItem> drops = new ArrayList<>();
    private final List<Door> doors = new ArrayList<>();
    private final List<Occupation> cannons = new ArrayList<>();
    private final List<Occupation> trains = new ArrayList<>();
    private int nextDropSeq = 1;
    private final List<Integer> destroyedBricks = new ArrayList<>();
    private final List<Integer> pendingResults = new ArrayList<>();

    private boolean running;
    private int remainTime = -1;
    private int playTime;

    public abstract boolean hasRoundObjective();

    public abstract boolean hasKillObjective();

    public synchronized void resetMatch() {
        stats.clear();
        drops.clear();
        doors.clear();
        cannons.clear();
        trains.clear();
        nextDropSeq = 1;
        destroyedBricks.clear();
        pendingResults.clear();
        remainTime = -1;
        playTime = 0;
        running = true;
    }

    public synchronized boolean running() {
        return running;
    }

    public synchronized boolean finishMatch() {
        if (!running) {
            return false;
        }
        running = false;
        return true;
    }

    public synchronized PlayerStats registerPlayer(final int clientId, final boolean red, final String nickname) {
        PlayerStats existing = stats(clientId);
        if (existing != null) {
            return existing;
        }
        PlayerStats created = new PlayerStats(clientId, red, nickname);
        stats.add(created);
        return created;
    }

    public synchronized PlayerStats stats(final int clientId) {
        for (PlayerStats entry : stats) {
            if (entry.clientId() == clientId) {
                return entry;
            }
        }
        return null;
    }

    public synchronized List<PlayerStats> allStats() {
        return new ArrayList<>(stats);
    }

    public synchronized int remainTime() {
        return remainTime;
    }

    public synchronized int playTime() {
        return playTime;
    }

    public synchronized void updateTime(final int remainTime, final int playTime) {
        this.remainTime = remainTime;
        this.playTime = playTime;
    }

    public synchronized boolean destroyBrick(final int brick) {
        if (destroyedBricks.size() >= 10000) { //prevent to many destroyed bricks
            return false;
        }
        if (destroyedBricks.contains(brick)) {
            return false;
        }
        destroyedBricks.add(brick);
        return true;
    }

    public synchronized List<Integer> destroyedBricks() {
        return new ArrayList<>(destroyedBricks);
    }

    public synchronized DroppedItem addDrop(final String code, final int ammo, final int subAmmo, final float x, final float y,
                                            final float z) {
        if (drops.size() >= 64) { //prevent to many dropped items
            return null;
        }
        DroppedItem item = new DroppedItem(nextDropSeq++, code, ammo, subAmmo, x, y, z);
        drops.add(item);
        return item;
    }

    public synchronized DroppedItem takeDrop(final int seq) {
        for (int index = 0; index < drops.size(); index++) {
            if (drops.get(index).seq() == seq) {
                return drops.remove(index);
            }
        }
        return null;
    }

    public synchronized List<DroppedItem> drops() {
        return new ArrayList<>(drops);
    }

    public synchronized void door(final int id, final boolean open) {
        for (int index = 0; index < doors.size(); index++) {
            if (doors.get(index).id() == id) {
                doors.set(index, new Door(id, open));
                return;
            }
        }
        if (doors.size() < 1000) { //prevent to large amount
            doors.add(new Door(id, open));
        }
    }

    public synchronized List<Door> doors() {
        return new ArrayList<>(doors);
    }

    public synchronized int occupyCannon(final int cannon, final int clientId) {
        return occupy(cannons, cannon, clientId);
    }

    public synchronized boolean freeCannon(final int cannon, final int clientId) {
        return free(cannons, cannon, clientId);
    }

    public synchronized int occupyTrain(final int train, final int clientId) {
        return occupy(trains, train, clientId);
    }

    public synchronized boolean freeTrain(final int train, final int clientId) {
        return free(trains, train, clientId);
    }

    public synchronized List<Occupation> cannons() {
        return new ArrayList<>(cannons);
    }

    public synchronized List<Occupation> trains() {
        return new ArrayList<>(trains);
    }

    public synchronized int[] releaseCannons(final int clientId) {
        return release(cannons, clientId);
    }

    public synchronized int[] releaseTrains(final int clientId) {
        return release(trains, clientId);
    }

    private int occupy(final List<Occupation> occupations, final int object, final int clientId) {
        for (Occupation occupation : occupations) {
            if (occupation.object() == object) {
                return occupation.clientId();
            }
        }
        if (occupations.size() >= 1000) { //prevent to large amount
            return -1;
        }
        occupations.add(new Occupation(object, clientId));
        return clientId;
    }

    private boolean free(final List<Occupation> occupations, final int object, final int clientId) {
        return occupations.remove(new Occupation(object, clientId));
    }

    private int[] release(final List<Occupation> occupations, final int clientId) {
        List<Occupation> released = occupations.stream().filter(occupation -> occupation.clientId() == clientId).toList();
        occupations.removeAll(released);
        return released.stream().mapToInt(Occupation::object).toArray();
    }

    public synchronized void pendingResults(final Collection<Integer> clientIds) {
        pendingResults.clear();
        pendingResults.addAll(clientIds);
    }

    public synchronized boolean resultDone(final int clientId) {
        pendingResults.remove(Integer.valueOf(clientId));
        return pendingResults.isEmpty();
    }

}
