package de.brickforceaurora.server.net.protocol.data;

public record RoomInfo(int id, RoomType type, String title, boolean locked, RoomStatus status, int currentPlayerCount, int maxPlayerCount,
    int mapId, String mapAlias, int goal, int timeLimit, int weaponOption, int ping, int score1, int score2, int countryFilter,
    boolean isBreakInto, boolean isDropItem, boolean isWanted, int squad, int squadCounter) {

}
