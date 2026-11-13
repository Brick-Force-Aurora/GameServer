package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.room.PeerEndpoints;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundRendezvousPointPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRandezvousPointPacket;
import de.brickforceaurora.server.net.rendezvous.RendezvousServer;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class RendezvousListener_ implements INetListener {

    @PacketHandler
    public void onRendezvousPoint(final NetContext<ServerboundRandezvousPointPacket> context) {
        BFClient client = context.client();
        ServerboundRandezvousPointPacket packet = context.packet();

        PeerEndpoints endpoints = PeerEndpoints.validated(client, packet.localIp(), packet.localPort(), packet.remoteIp(),
            packet.remotePort());
        client.attrSet(PeerEndpoints.ATTR_ENDPOINTS, endpoints);
        client.attrSet(RendezvousServer.ATTR_REPORTED, true);
        MatchServerApp.logger().debug("Endpoints of '{0}': local {1}:{2}, remote {3}:{4}", client.name(), endpoints.localIp(),
            endpoints.localPort(), endpoints.remoteIp(), endpoints.remotePort());

        // update endpoints
        Room room = client.attr(Room.ATTR_ROOM, Room.class);
        if (room != null) {
            room.broadcast(client, new ClientboundRendezvousPointPacket().clientId(client.id()).localIp(endpoints.localIp())
                .localPort(endpoints.localPort()).remoteIp(endpoints.remoteIp()).remotePort(endpoints.remotePort()));
        }
    }

}
