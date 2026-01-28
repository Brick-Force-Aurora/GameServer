package de.brickforceaurora.gameserver.net;

import java.net.InetSocketAddress;

import de.brickforceaurora.gameserver.legacy.data.ClientData;
import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.channel.Channel;

public final class BFClient {

    private final Channel channel;

    private String identifier;

    private String name;
    private String ip;
    private int port;
    private int id;
    
    private boolean initialized = false;
    
    volatile boolean shouldKeepAlive = false;
    volatile long netTime = 0;
    
    private final ClientData data;

    public BFClient(Channel channel) {
        this.channel = channel;
        this.ip = ((InetSocketAddress) channel.remoteAddress()).getAddress().getHostAddress();
        this.data = new ClientData(this);
    }

    public void init(String name, int id) {
        if (this.initialized) {
            throw new IllegalStateException("Already initialized");
        }
        this.name = name;
        this.identifier = name + '-' + id + '-' + ip;
        this.id = id;
        this.port = 6000 + id;
        this.initialized = true;
    }

    public boolean isLoggedIn() {
        return initialized;
    }

    public String identifier() {
        return identifier;
    }
    
    public Channel channel() {
        return channel;
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
        if (!channel.isOpen()) {
            return false;
        }
        channel.close();
        return true;
    }
    
    public void send(IClientboundPacket packet) {
        channel.writeAndFlush(packet);
    }

}
