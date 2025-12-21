package de.brickforceaurora.server.net;

import io.netty.channel.Channel;

public final class ClientReference {

    public Channel channel;
    public byte[] buffer = new byte[8192];
    public int seq = -1;
    public float toleranceTime = 0f;

    public ClientReference(Channel channel) {
        this.channel = channel;
    }

    public void disconnect() {
        channel.close();
    }
}
