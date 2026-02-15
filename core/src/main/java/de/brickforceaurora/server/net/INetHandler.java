package de.brickforceaurora.server.net;

import de.brickforceaurora.server.net.protocol.IPacket;

@FunctionalInterface
public interface INetHandler<P extends IPacket> {

    void onPacket(NetContext<P> context) throws Throwable;

}
