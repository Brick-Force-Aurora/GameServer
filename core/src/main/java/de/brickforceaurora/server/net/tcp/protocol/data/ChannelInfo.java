package de.brickforceaurora.server.net.tcp.protocol.data;

import de.brickforceaurora.server.net.tcp.protocol.data.api.IChannelInfo;

public record ChannelInfo(int id, ChannelMode mode, String name, String ip, int port, int users, int maxUsers, int country, int minRank,
    int maxRank, int xpBonus, int fpBonus, int starRatingLimit) implements IChannelInfo {}
