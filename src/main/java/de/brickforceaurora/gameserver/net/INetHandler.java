package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.IPacket;

@FunctionalInterface
public interface INetHandler<P extends IPacket> {

    void onPacket(NetContext<P> context) throws Throwable;

}
