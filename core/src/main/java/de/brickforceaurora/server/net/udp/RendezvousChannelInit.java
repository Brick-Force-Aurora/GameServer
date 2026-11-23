package de.brickforceaurora.server.net.udp;

import de.brickforceaurora.server.ServerApp;
import de.brickforceaurora.server.net.udp.protocol.P2PClientboundPacketEncoder;
import de.brickforceaurora.server.net.udp.protocol.P2PServerboundPacketDecoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

final class RendezvousChannelInit<S extends ServerApp<S>> extends ChannelInitializer<Channel> {

    private final RendezvousManager<S> server;

    RendezvousChannelInit(final RendezvousManager<S> server) {
        this.server = server;
    }

    @Override
    protected void initChannel(final Channel channel) throws Exception {
        final ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new RendezvousRateLimit<>(server));
        pipeline.addLast(new P2PClientboundPacketEncoder(server.logger()));
        pipeline.addLast(new P2PServerboundPacketDecoder(server.logger()));
        pipeline.addLast(new RendezvousHandler<>(server));
    }

}
