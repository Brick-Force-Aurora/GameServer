package de.brickforceaurora.server.database.model;

public record UserProfile(int accountId, String nickname, int xp, int rank, int tutorials, int countryFilter, boolean tosAccepted,
                          int extraSlots, int firstLoginFp, int point, int brick, int cash, int freeCoin, int starDust) {

    public static UserProfile defaults(int accountId) {
        return new UserProfile(accountId, null, 0, 0, 3, -1, true, 9, 0, 20000, 0, 0, 0, 0);
    }

    public boolean hasCharacter() {
        return nickname != null;
    }

    public UserProfile withNickname(String nickname) {
        return new UserProfile(accountId, nickname, xp, rank, tutorials, countryFilter, tosAccepted, extraSlots, firstLoginFp, point,
                brick, cash, freeCoin, starDust);
    }

}
