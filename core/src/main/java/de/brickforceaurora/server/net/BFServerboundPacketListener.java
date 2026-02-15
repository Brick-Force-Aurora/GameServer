package de.brickforceaurora.server.net;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
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
        netManager.handlePacket(client, msg);
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        netManager.clientDisconnected(client);
        ctx.fireChannelInactive();
    }

}
