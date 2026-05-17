package de.brickforceaurora.server.net.listener;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.login.ILoginHandler;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import de.brickforceaurora.server.net.protocol.serverbound.aurora.ServerboundAuroraHeartbeatPacket;
import de.brickforceaurora.server.util.TimeMath;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class ConnectionListener_ implements INetListener {

    @PacketHandler
    public void onHeartbeat(final NetContext<ServerboundAuroraHeartbeatPacket> context) {
        BFClient client = context.client();
        if (!client.wasChallanged()) {
            // Don't allow heartbeat from unchallanged client
            return;
        }
        if (client.attrHas(ILoginHandler.ATTR_REQUEST_LOGIN_TIME)) {
            long loginTime = client.attr(ILoginHandler.ATTR_REQUEST_LOGIN_TIME, long.class);
            if (TimeMath.calculateDifference(context.manager().netTime(), loginTime) > ILoginHandler.LOGIN_TIMEOUT_TIME) {
                // Don't allow any keep alive anymore :)
                return;
            }
        }
        context.manager().keepClientAlive(context.client());
        if (context.packet().requestsHeartbeat()) {
            context.client().send(ProtocolExtension.PACKET_HEARTBEAT);
        }
    }

}
