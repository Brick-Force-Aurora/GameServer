package de.brickforceaurora.server.match.gamemode;

public abstract class MatchGameData extends GameData{
    public abstract int mapId();
    public abstract boolean canJoinMidGame();

    public abstract boolean hasRoundObjective();

    public abstract boolean hasKillObjective();
}
