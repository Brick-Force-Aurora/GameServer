package de.brickforceaurora.gameserver.net;
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

    public Channel(
            int id,
            ChannelMode mode,
            String name,
            String ip,
            int port,
            int userCount,
            int maxUserCount,
            int country,
            byte minLvRank,
            byte maxLvRank,
            short xpBonus,
            short fpBonus,
            int limitStarRate
    ) {
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

    /* ===== Logic ===== */

    public boolean isSmartQuickJoin() {
        return mode == ChannelMode.NEWBIE || mode == ChannelMode.BATTLE;
    }

    public boolean isLimitStarRate() {
        if (mode == ChannelMode.MAPEDIT) {
            return false;
        }
        return limitStarRate > 0 && limitStarRate < 10000;
    }

    public boolean isUsableLevel(int lvRank) {
        return lvRank >= minLvRank && lvRank <= maxLvRank;
    }

    public int compare(Channel other) {
        int r = Integer.compare(this.mode.id, other.mode.id);
        return r != 0 ? r : Integer.compare(this.id, other.id);
    }
}
