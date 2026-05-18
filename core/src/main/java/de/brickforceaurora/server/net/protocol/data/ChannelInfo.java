package de.brickforceaurora.server.net.protocol.data;

public record ChannelInfo(int id, ChannelMode mode, String name, String ip, int port, int userCount, int maxUserCount, int country,
    int minLvRank, int maxLvRank, int xpBonus, int fpBonus, int limitStarRate) {}
