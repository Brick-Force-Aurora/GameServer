package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

final class BFChannelInboundListener extends SimpleChannelInboundHandler<IServerboundPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, IServerboundPacket msg) throws Exception {

    }

}
