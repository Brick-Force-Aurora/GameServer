package de.brickforceaurora.server.net.protocol.data;

import de.brickforceaurora.server.net.protocol.data.api.IRoomInfo;

public record RoomInfo(int id, RoomType type, String title, boolean passwordLocked, RoomStatus status, int players, int maxPlayers,
    int mapId, String mapAlias, int goal, int timeLimit, int weaponOption, int ping, int blueScore, int redScore, CountryFilter countryFilter,
    boolean allowsLateJoining, boolean weaponDropEnabled, boolean wantedEnabled, int squad, int squadCounter) implements IRoomInfo {

}
