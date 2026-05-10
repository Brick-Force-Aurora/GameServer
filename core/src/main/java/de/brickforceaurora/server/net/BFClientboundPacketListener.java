package de.brickforceaurora.server.net;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraDisconnectPacket;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

final class BFClientboundPacketListener extends ChannelOutboundHandlerAdapter {

    private final NetManager<?> netManager;
    private final BFClient client;
    
    private volatile boolean disconnecting = false;

    public BFClientboundPacketListener(final NetManager<?> netManager, final BFClient client) {
        this.netManager = netManager;
        this.client = client;
    }

    @Override
    public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
        if (disconnecting || !(msg instanceof final IClientboundPacket packet)) {
            ctx.write(msg, promise);
            return;
        }
        if (netManager.handlePacket(client, packet)) {
            // Intercept packet cause that's what was asked 
            promise.cancel(true);
            return;
        }
        ctx.write(msg, promise);
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (disconnecting) {
            netManager.logger().error("Error in clientbound listener of player '{0}' while disconnecting", cause, client);
            ctx.close();
            return;
        }
        disconnecting = true;
        netManager.logger().error("Error in clientbound listener of player '{0}'", cause, client);
        ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Something went wrong")).addListener(ChannelFutureListener.CLOSE);
    }

}
