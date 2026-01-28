package de.brickforceaurora.gameserver.legacy.channel;
public final class Channel {

    /* ===== Immutable config ===== */

    public final int id;
    public final ChannelMode mode;
    public final String name;
    public final String ip;
    public final int port;

    /* ===== Runtime state ===== */

    public int userCount;
    public int maxUserCount;
    public int country;
    public int minLvRank;
    public int maxLvRank;
    public int xpBonus;
    public int fpBonus;
    public int limitStarRate;

    public Channel(int id, ChannelMode mode, String name, String ip, int port, int userCount, int maxUserCount, int country, int minLvRank, int maxLvRank, int xpBonus, int fpBonus, int limitStarRate) {
        this.id = id;
        this.mode = mode;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.userCount = userCount;
        this.maxUserCount = maxUserCount;
        this.country = country;
        this.minLvRank = minLvRank;
        this.maxLvRank = maxLvRank;
        this.xpBonus = xpBonus;
        this.fpBonus = fpBonus;
        this.limitStarRate = limitStarRate;
    }
}
