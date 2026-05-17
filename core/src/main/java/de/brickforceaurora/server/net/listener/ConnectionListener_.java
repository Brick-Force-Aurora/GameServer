package de.brickforceaurora.server.net.listener;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundHeartbeatPacket;
import de.brickforceaurora.server.util.TimeMath;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class ConnectionListener_ implements INetListener {

    @PacketHandler
    public void onHeartbeat(final NetContext<ServerboundHeartbeatPacket> context) {
        BFClient client = context.client();
        if (client.attrHas(HandshakeListener.ATTR_REQUEST_LOGIN_TIME)) {
            long loginTime = client.attr(HandshakeListener.ATTR_REQUEST_LOGIN_TIME, long.class);
            if (TimeMath.calculateDifference(context.manager().netTime(), loginTime) > HandshakeListener.LOGIN_TIMEOUT_TIME) {
                // Don't allow any keep alive anymore :)
                return;
            }
        }
        context.manager().keepClientAlive(context.client());
    }

}
