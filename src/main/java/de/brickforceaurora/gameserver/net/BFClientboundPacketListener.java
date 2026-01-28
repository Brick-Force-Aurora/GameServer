package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

final class BFClientboundPacketListener extends ChannelOutboundHandlerAdapter {

    private final NetManager netManager;
    private final BFClient client;

    public BFClientboundPacketListener(final NetManager netManager, final BFClient client) {
        this.netManager = netManager;
        this.client = client;
    }

    @Override
    public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
        if (!(msg instanceof final IClientboundPacket packet)) {
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
