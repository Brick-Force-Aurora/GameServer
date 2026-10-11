package de.brickforceaurora.server.match.gamemode;

public abstract class GameData {

    public int mapId() {
        return 0;
    }

    public String mapAlias() {
        return "";
    }

    public int goal() {
        return 0;
    }

    public int timeLimit() {
        return 0;
    }

    public int weaponOption() {
        return 0;
    }

    public boolean canJoinMidGame() {
        return false;
    }

    public boolean autoBalance() {
        return false;
    }

    public boolean wanted() {
        return false;
    }

    public boolean drop() {
        return false;
    }

    public int blueScore() {
        return 0;
    }

    public int redScore() {
        return 0;
    }
}