package de.brickforceaurora.server.database.model;

public record UserStats(int accountId, int kill, int death, int assist, int mission, int score, int heavy, int assault, int sniper,
                        int subMachine, int handGun, int melee, int special) {

    public static UserStats defaults(int accountId) {
        return new UserStats(accountId, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

}
