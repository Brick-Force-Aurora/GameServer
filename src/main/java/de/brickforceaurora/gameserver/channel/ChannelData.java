package de.brickforceaurora.gameserver.channel;

public record ChannelData(int maxUsers, int country, int minLevel, int maxLevel, int xpBonus, int fpBonus, int limitStarRate) {

    public static final ChannelData DEFAULT = new ChannelData(256, 1, 0, Integer.MAX_VALUE, 0, 0, 0);

}
