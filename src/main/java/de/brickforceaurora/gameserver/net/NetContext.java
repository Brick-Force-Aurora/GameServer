package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.IPacket;

public final class NetContext<P extends IPacket> {

    private final NetManager manager;
    private final BFClient client;
    private final P packet;

    private volatile boolean intercept = false;

    public NetContext(NetManager manager, BFClient client, P packet) {
        this.manager = manager;
        this.client = client;
        this.packet = packet;
    }

    public boolean intercepts() {
        return intercept;
    }

    public void intercepts(boolean intercept) {
        this.intercept = intercept;
    }

    public NetManager manager() {
        return manager;
    }

    public BFClient client() {
        return client;
    }

    public P packet() {
        return packet;
    }

}
