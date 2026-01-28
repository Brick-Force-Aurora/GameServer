package de.brickforceaurora.gameserver.channel;

import de.brickforceaurora.gameserver.legacy.channel.ChannelMode;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.concurrent.atomic.AtomicInteger;

public class ChannelHolder {

    private final AtomicInteger idCount = new AtomicInteger();

    private final ChannelMode mode;
    private final int startId;

    public ChannelHolder(ChannelMode mode) {
        this.mode = mode;
        idCount.set(startId = ((mode.id - 1) * 1000));
    }

    public int startId() {
        return startId;
    }

    public int channelCount() {
        return idCount.get() - startId;
    }

    public Channel newChannel(String name) {
        return new Channel(idCount.getAndIncrement(), name, mode);
    }
}
