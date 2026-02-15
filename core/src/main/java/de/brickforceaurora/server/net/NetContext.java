package de.brickforceaurora.server.net;

import de.brickforceaurora.server.net.protocol.IPacket;

public final class NetContext<P extends IPacket> {

    private final NetManager<?> manager;
    private final BFClient client;
    private final P packet;

    private volatile boolean intercept = false;

    public NetContext(final NetManager<?> manager, final BFClient client, final P packet) {
        this.manager = manager;
        this.client = client;
        this.packet = packet;
    }

    public boolean intercepts() {
        return intercept;
    }

    public void intercepts(final boolean intercept) {
        this.intercept = intercept;
    }

    public NetManager<?> manager() {
        return manager;
    }

    public BFClient client() {
        return client;
    }

    public P packet() {
        return packet;
    }

}
