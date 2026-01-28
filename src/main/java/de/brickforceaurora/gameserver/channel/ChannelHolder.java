package de.brickforceaurora.gameserver.channel;

import java.util.concurrent.atomic.AtomicInteger;

import de.brickforceaurora.gameserver.legacy.channel.ChannelMode;

public class ChannelHolder {

    private final AtomicInteger idCount = new AtomicInteger();

    private final ChannelMode mode;
    private final int startId;

    public ChannelHolder(final ChannelMode mode) {
        this.mode = mode;
        idCount.set(startId = (mode.id - 1) * 1000);
    }

    public int startId() {
        return startId;
    }

    public int channelCount() {
        return idCount.get() - startId;
    }

    public Channel newChannel(final String name) {
        return new Channel(idCount.getAndIncrement(), name, mode);
    }
}
