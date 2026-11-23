package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.net.tcp.INetListener;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.PacketHandler;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundRoaminPacket;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundSvcEnterListPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.ClientInfo;
import de.brickforceaurora.server.net.tcp.protocol.data.api.IClientInfo;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundChannelPlayerListPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundRoaminPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class ChannelListener_ implements INetListener {

    @PacketHandler
    public void onRoamIn(final NetContext<ServerboundRoaminPacket> context) {
        context.client().send(new ClientboundRoaminPacket().targetChannelId(1)); // TODO: WHAT IS OUR CHANNEL ID?!?!?!?!?
    }

    @PacketHandler
    public void onChannelPlayerList(final NetContext<ServerboundChannelPlayerListPacket> context) {
        context.client().send(new ClientboundSvcEnterListPacket().clients(context.manager().activeClients().map(client -> {
            // The profile is set once the account was loaded
            final UserProfile profile = client.attr(ClientListener.ATTR_PROFILE, UserProfile.class);
            return new ClientInfo(client.id(), client.name(), profile == null ? 0 : profile.xp(), profile == null ? 0 : profile.rank());
        }).toArray(IClientInfo[]::new)));
    }
}
