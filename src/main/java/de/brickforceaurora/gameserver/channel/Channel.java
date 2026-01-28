package de.brickforceaurora.gameserver.channel;

import de.brickforceaurora.gameserver.legacy.channel.ChannelMode;
import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;

public class Channel {

    private final ObjectList<BFClient> clients = new ObjectArrayList<>();

    private final int id;
    private final String name;
    private final ChannelMode mode;

    private final ChannelData data = ChannelData.DEFAULT;

    public Channel(int id, String name, ChannelMode mode) {
        this.id = id;
        this.name = name;
        this.mode = mode;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public ChannelMode mode() {
        return mode;
    }

    public ObjectList<BFClient> clients() {
        return clients;
    }

    public int userCount() {
        return clients.size();
    }

    public ChannelData data() {
        return data;
    }

    public void broadcast(IClientboundPacket packet) {
        for (BFClient client : clients) {
            client.send(packet);
        }
    }

}
