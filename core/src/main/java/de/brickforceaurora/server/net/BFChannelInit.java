package de.brickforceaurora.server.net;

import java.net.InetAddress;

import de.brickforceaurora.server.net.protocol.BFClientboundPacketEncoder;
import de.brickforceaurora.server.net.protocol.BFServerboundPacketDecoder;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraDisconnectPacket;
import de.brickforceaurora.server.util.RateLimiter;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

final class BFChannelInit extends ChannelInitializer<SocketChannel> {

    private final NetManager<?> netManager;
    private final RateLimiter<InetAddress> rateLimiter;

    public BFChannelInit(final NetManager<?> netManager) {
        this.netManager = netManager;
        this.rateLimiter = netManager.rateLimiter();
    }

    @Override
    protected void initChannel(final SocketChannel channel) throws Exception {
        final ChannelPipeline pipeline = channel.pipeline();

        if (!rateLimiter.acquire(channel.remoteAddress().getAddress())) {
            // We add this outbound encoder to send the disconnect packet
            pipeline.addLast(new BFClientboundPacketEncoder(netManager.logger(), null));
            channel.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Rate limited, try again later")).addListener(ChannelFutureListener.CLOSE);
            return;
        }
        final BFClient client = new BFClient(channel);
        client.shouldKeepAlive = true;
        
        // Inbound
        pipeline.addLast(new BFServerboundPacketDecoder(netManager.logger(), client));
        pipeline.addLast(new BFServerboundPacketListener(netManager, client));
        // Outbound
        // pipeline.addLast(new BFClientboundPacketListener(netManager, client)); // We don't need this right now so its commented out
        pipeline.addLast(new BFClientboundPacketEncoder(netManager.logger(), client));

        netManager.clientConnected(client);
    }

}
