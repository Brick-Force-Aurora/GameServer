package de.brickforceaurora.gameserver.net.listener;

import de.brickforceaurora.gameserver.channel.Channel;
import de.brickforceaurora.gameserver.channel.ChannelManager;
import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.INetListener;
import de.brickforceaurora.gameserver.net.NetContext;
import de.brickforceaurora.gameserver.net.PacketHandler;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundSvcEnterListPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundChannelPlayerListPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class ChannelListenerTemplate implements INetListener {

    private final ChannelManager channelManager;

    public ChannelListenerTemplate(final ChannelManager channelManager) {
        this.channelManager = channelManager;
    }

    @PacketHandler
    public void onChannelPlayerList(final NetContext<ServerboundChannelPlayerListPacket> context) {
        final BFClient client = context.client();

        //send player list of current channel where player is in
        client.send(new ClientboundSvcEnterListPacket()
            .clients(channelManager.channels().toArray(Channel[]::new)[0].clients().toArray(BFClient[]::new)));
    }
}
