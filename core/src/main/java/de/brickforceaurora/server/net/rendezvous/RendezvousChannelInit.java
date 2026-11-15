package de.brickforceaurora.server.net.rendezvous;

import de.brickforceaurora.server.IBrickForceServer;
import de.brickforceaurora.server.net.rendezvous.protocol.P2PClientboundPacketEncoder;
import de.brickforceaurora.server.net.rendezvous.protocol.P2PServerboundPacketDecoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import me.lauriichan.snowframe.ISnowFrameApp;

final class RendezvousChannelInit<S extends ISnowFrameApp<S> & IBrickForceServer> extends ChannelInitializer<Channel> {

    private final RendezvousManager<S> server;

    RendezvousChannelInit(final RendezvousManager<S> server) {
        this.server = server;
    }

    @Override
    protected void initChannel(final Channel channel) throws Exception {
        final ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new P2PClientboundPacketEncoder(server.logger()));
        pipeline.addLast(new P2PServerboundPacketDecoder(server.logger()));
        pipeline.addLast(new RendezvousHandler<>(server));
    }

}
