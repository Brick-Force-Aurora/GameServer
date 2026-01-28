package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.channel.Channel;
import me.lauriichan.snowframe.signal.ISignal;

public final class NetSignal {

    private NetSignal() {
        throw new UnsupportedOperationException();
    }

    public static record ServerStarted(NetManager netManager) implements ISignal {}

    public static record ClientConnected(NetManager netManager, BFClient client) implements ISignal {}

    public static record ClientDisconnected(NetManager netManager, BFClient client) implements ISignal {}
    
    public static record ClientJoinedChannel(NetManager netManager, BFClient client, Channel channel) implements ISignal {}

}
