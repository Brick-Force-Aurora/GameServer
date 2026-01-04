package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

final class BFServerboundPacketListener extends SimpleChannelInboundHandler<IServerboundPacket> {

    private final NetManager netManager;
    private final BFClient client;

    public BFServerboundPacketListener(NetManager netManager, BFClient client) {
        this.netManager = netManager;
        this.client = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IServerboundPacket msg) throws Exception {
        netManager.handlePacket(client, msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        netManager.clientDisconnected(client);
        ctx.fireChannelInactive();
    }

}
