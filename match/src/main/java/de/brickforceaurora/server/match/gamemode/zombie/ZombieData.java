package de.brickforceaurora.server.match.gamemode.zombie;

import de.brickforceaurora.server.match.gamemode.TeamGameData;

public class ZombieData extends TeamGameData {

    @Override
    public boolean autoBalance() {
        return false;
    }

    @Override
    public int mapId() {
        return 0;
    }

    @Override
    public boolean canJoinMidGame() {
        return false;
    }

    @Override
    public boolean hasRoundObjective() {
        return false;
    }

    @Override
    public boolean hasKillObjective() {
        return false;
    }
}
