package de.brickforceaurora.server.net.tcp;

import de.brickforceaurora.server.net.tcp.protocol.IPacket;

@FunctionalInterface
public interface INetHandler<P extends IPacket> {

    void onPacket(NetContext<P> context) throws Throwable;

}
