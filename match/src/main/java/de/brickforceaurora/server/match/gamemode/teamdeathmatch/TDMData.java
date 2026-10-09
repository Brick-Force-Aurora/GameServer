package de.brickforceaurora.server.match.gamemode.teamdeathmatch;

import de.brickforceaurora.server.match.gamemode.TeamGameData;

public class TDMData extends TeamGameData {
    boolean wanted;
    boolean drop; //TODO: Create Interface
    int goal;
    int timelimit;
    int weaponOption;
    int mapId;
    boolean canJoinMidGame;
    boolean autoBalance;
    String mapAlias;

    @Override
    public int mapId() {
        return mapId;
    }

    @Override
    public boolean canJoinMidGame() {
        return canJoinMidGame;
    }

    @Override
    public boolean hasRoundObjective() {
        return false;
    }

    @Override
    public boolean hasKillObjective() {
        return true;
    }

    @Override
    public boolean autoBalance() {
        return autoBalance;
    }
}
