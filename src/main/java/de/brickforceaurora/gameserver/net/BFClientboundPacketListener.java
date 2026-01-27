package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

final class BFClientboundPacketListener extends ChannelOutboundHandlerAdapter {

    private final NetManager netManager;
    private final BFClient client;

    public BFClientboundPacketListener(NetManager netManager, BFClient client) {
        this.netManager = netManager;
        this.client = client;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (!(msg instanceof IClientboundPacket packet)) {
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

}
