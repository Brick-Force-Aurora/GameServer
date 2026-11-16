package de.brickforceaurora.server.match.game.mode.teamdeathmatch;

import de.brickforceaurora.server.match.game.TeamGameData;

public class TDMData extends TeamGameData {

    private int mapId;
    private String mapAlias = "";
    private int goal;
    private int timeLimit;
    private int weaponOption;
    private boolean canJoinMidGame;
    private boolean autoBalance;
    private boolean wanted;
    private boolean drop;

    @Override
    public int mapId() {
        return mapId;
    }

    @Override
    public String mapAlias() {
        return mapAlias;
    }

    @Override
    public int goal() {
        return goal;
    }

    @Override
    public int timeLimit() {
        return timeLimit;
    }

    @Override
    public int weaponOption() {
        return weaponOption;
    }

    @Override
    public boolean canJoinMidGame() {
        return canJoinMidGame;
    }

    @Override
    public boolean autoBalance() {
        return autoBalance;
    }

    @Override
    public boolean wanted() {
        return wanted;
    }

    @Override
    public boolean drop() {
        return drop;
    }

    @Override
    public boolean hasRoundObjective() {
        return false;
    }

    @Override
    public boolean hasKillObjective() {
        return true;
    }

    public TDMData mapId(final int mapId) {
        this.mapId = mapId;
        return this;
    }

    public TDMData mapAlias(final String mapAlias) {
        this.mapAlias = mapAlias != null ? mapAlias : "";
        return this;
    }

    public TDMData goal(final int goal) {
        this.goal = goal;
        return this;
    }

    public TDMData timeLimit(final int timelimit) {
        this.timeLimit = timelimit;
        return this;
    }

    public TDMData weaponOption(final int weaponOption) {
        this.weaponOption = weaponOption;
        return this;
    }

    public TDMData canJoinMidGame(final boolean canJoinMidGame) {
        this.canJoinMidGame = canJoinMidGame;
        return this;
    }

    public TDMData autoBalance(final boolean autoBalance) {
        this.autoBalance = autoBalance;
        return this;
    }

    public TDMData wanted(final boolean wanted) {
        this.wanted = wanted;
        return this;
    }

    public TDMData drop(final boolean drop) {
        this.drop = drop;
        return this;
    }
}