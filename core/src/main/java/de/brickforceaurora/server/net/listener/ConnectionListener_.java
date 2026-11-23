package de.brickforceaurora.server.net.listener;

import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.INetListener;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.PacketHandler;
import de.brickforceaurora.server.net.tcp.login.ILoginHandler;
import de.brickforceaurora.server.net.tcp.protocol.ProtocolExtension;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.aurora.ServerboundAuroraHeartbeatPacket;
import de.brickforceaurora.server.util.TimeMath;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class ConnectionListener_ implements INetListener {

    @PacketHandler
    public void onHeartbeat(final NetContext<ServerboundAuroraHeartbeatPacket> context) {
        final BFClient client = context.client();
        if (!client.wasChallanged()) {
            // Don't allow heartbeat from unchallanged client
            return;
        }
        if (client.attrHas(ILoginHandler.ATTR_REQUEST_LOGIN_TIME)) {
            final Long loginTime = client.attr(ILoginHandler.ATTR_REQUEST_LOGIN_TIME, Long.class);
            if (loginTime != null
                && TimeMath.calculateDifference(context.manager().netTime(), loginTime) > ILoginHandler.LOGIN_TIMEOUT_TIME) {
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
