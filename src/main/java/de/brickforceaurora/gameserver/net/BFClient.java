package de.brickforceaurora.gameserver.net;

import java.net.InetSocketAddress;

import de.brickforceaurora.gameserver.data.ClientData;
import io.netty.channel.Channel;

public final class BFClient {

    private final Channel channel;

    private String identifier;

    private String name;
    private String ip;
    private int port;
    private int id;

    final byte[] buffer = new byte[8192];

    private final ClientData data;

    public BFClient(Channel channel) {
        this.channel = channel;
        this.ip = ((InetSocketAddress) channel.remoteAddress()).getAddress().getHostAddress();
        this.data = new ClientData(this);
    }

    public void init(String name, int id) {
        if (this.name != null) {
            throw new IllegalStateException("Already logged in");
        }
        this.name = name;
        this.identifier = name + '-' + id + '-' + ip;
        this.id = id;
        this.port = 6000 + id;
    }

    public String identifier() {
        return identifier;
    }

    public String name() {
        return name;
    }

    public int port() {
        return port;
    }

    public String ip() {
        return ip;
    }

    public int id() {
        return id;
    }
    
    public ClientData data() {
        return data;
    }
    
    public boolean disconnect() {
        return disconnect(true);
    }
    
    public boolean disconnect(boolean send) {
        return false;
    }

}
