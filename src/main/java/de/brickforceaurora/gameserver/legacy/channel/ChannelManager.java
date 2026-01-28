package de.brickforceaurora.gameserver.legacy.channel;

import java.util.ArrayList;
import java.util.List;

public final class ChannelManager {

    private final List<ChannelReference> channels;

    public ChannelManager() {
        this.channels = new ArrayList<>();
        setupDefaultChannels();
    }

    public List<ChannelReference> getChannels() {
        return channels;
    }

    public ChannelReference getChannelById(final int id) {
        for (final ChannelReference ref : channels) {
            if (ref.channel.id == id) {
                return ref;
            }
        }
        return null;
    }

    public ChannelReference getDefaultChannel() {
        if (channels.isEmpty()) {
            return null;
        }
        return channels.get(0);
    }

    public void addChannel(final Channel channel) {
        channels.add(new ChannelReference(channel));
    }

    public void addChannel(final int id, final ChannelMode mode, final String name) {
        final Channel channel = new Channel(id, mode, name, "", 5000, 1, 16, 1, 0, 66, 0, 0, 0);
        channels.add(new ChannelReference(channel));
    }

    private void setupDefaultChannels() {
        addChannel(1, ChannelMode.BATTLE, "Play");
        addChannel(2, ChannelMode.MAP_EDIT, "Build");
    }

    public void shutdown() {
        for (final ChannelReference channel : channels) {
            channel.shutdown();
        }
        channels.clear();
    }
}
