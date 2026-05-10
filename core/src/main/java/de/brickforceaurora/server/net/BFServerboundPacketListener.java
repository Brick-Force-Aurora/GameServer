package de.brickforceaurora.server.net;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.aurora.ClientboundAuroraDisconnectPacket;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

final class BFServerboundPacketListener extends SimpleChannelInboundHandler<IServerboundPacket> {
    
    private final NetManager<?> netManager;
    private final BFClient client;
    
    public BFServerboundPacketListener(final NetManager<?> netManager, final BFClient client) {
        this.netManager = netManager;
        this.client = client;
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final IServerboundPacket msg) throws Exception {
        if (msg.requiresLogIn() && !client.isLoggedIn()) {
            ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Client has to be logged in")).addListener(ChannelFutureListener.CLOSE);
            return;
        }
        netManager.handlePacket(client, msg);
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        netManager.clientDisconnected(client);
        ctx.fireChannelInactive();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        netManager.logger().error("Error in serverbound listener of player '{0}'", cause, client);
        ctx.writeAndFlush(new ClientboundAuroraDisconnectPacket().message("Server: Something went wrong")).addListener(ChannelFutureListener.CLOSE);
    }

}
