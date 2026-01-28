package de.brickforceaurora.gameserver.channel;

import de.brickforceaurora.gameserver.legacy.channel.ChannelMode;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import me.lauriichan.snowframe.util.Enum2ObjectMap;

public class ChannelManager {

    private final Enum2ObjectMap<ChannelMode, ChannelHolder> mode2channel = new Enum2ObjectMap<>(ChannelMode.class);
    private final Int2ObjectMap<Channel> channels = new Int2ObjectArrayMap<>();

    public ChannelManager() {
        for (final ChannelMode mode : ChannelMode.values()) {
            mode2channel.put(mode, new ChannelHolder(mode));
        }
    }

    public Channel newChannel(final ChannelMode mode, final String name) {
        final Channel channel = mode2channel.get(mode).newChannel(name);
        channels.put(channel.id(), channel);
        return channel;
    }

    public Channel getChannel(final int id) {
        return channels.get(id);
    }

    public ObjectList<Channel> channels(final ChannelMode mode) {
        final ChannelHolder holder = mode2channel.get(mode);
        final ObjectArrayList<Channel> channels = new ObjectArrayList<>(holder.channelCount());
        for (int offset = 0; offset < holder.channelCount(); offset++) {
            channels.add(this.channels.get(holder.startId() + offset));
        }
        return ObjectLists.unmodifiable(channels);
    }

    public ObjectCollection<Channel> channels() {
        return channels.values();
    }

}
