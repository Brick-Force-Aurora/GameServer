package de.brickforceaurora.server.net.rendezvous;

import de.brickforceaurora.server.IBrickForceServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;
import me.lauriichan.snowframe.ISnowFrameApp;

// Drops datagrams of senders that send too much, before they are decoded
final class RendezvousRateLimit<S extends ISnowFrameApp<S> & IBrickForceServer> extends ChannelInboundHandlerAdapter {

    private final RendezvousManager<S> server;

    RendezvousRateLimit(final RendezvousManager<S> server) {
        this.server = server;
    }

    @Override
    public void channelRead(final ChannelHandlerContext context, final Object message) {
        if (message instanceof final DatagramPacket datagram && !server.admit(datagram.sender())) {
            ReferenceCountUtil.release(message);
            return;
        }
        context.fireChannelRead(message);
    }

}
