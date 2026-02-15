package de.brickforceaurora.server.net;

import de.brickforceaurora.server.net.protocol.BFClientboundPacketEncoder;
import de.brickforceaurora.server.net.protocol.BFServerboundPacketDecoder;
import de.brickforceaurora.server.net.protocol.ProtocolExtension;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

final class BFChannelInit extends ChannelInitializer<SocketChannel> {

    private final NetManager<?> netManager;

    public BFChannelInit(final NetManager<?> netManager) {
        this.netManager = netManager;
    }

    @Override
    protected void initChannel(final SocketChannel channel) throws Exception {
        final BFClient client = new BFClient(channel);
        client.shouldKeepAlive = true;

        final ChannelPipeline pipeline = channel.pipeline();
        // Inbound
        pipeline.addLast(new BFServerboundPacketDecoder(netManager.logger(), client));
        pipeline.addLast(new BFServerboundPacketListener(netManager, client));
        // Outbound
        // pipeline.addLast(new BFClientboundPacketListener(netManager, client)); // We don't need this right now so its commented out
        pipeline.addLast(new BFClientboundPacketEncoder(netManager.logger(), client));

        netManager.clientConnected(client);

        channel.writeAndFlush(ProtocolExtension.PACKET_CONNECTED);
    }

}
