package de.brickforceaurora.server.net;

import de.brickforceaurora.server.core.GameServer;
import de.brickforceaurora.server.protocol.Msg2Handle;
import de.brickforceaurora.server.protocol.Msg4Recv;
import de.brickforceaurora.server.protocol.MsgBody;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public final class ReceiveHandler extends ChannelInboundHandlerAdapter {

    private final ClientReference client;

    public ReceiveHandler(ClientReference client) {
        this.client = client;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;

        int readable = in.readableBytes();
        if (readable <= 0) {
            return;
        }

        // copy into client buffer (matches your C# logic)
        in.readBytes(client.buffer, 0, readable);

        Msg4Recv recv = new Msg4Recv(client.buffer);
        if (recv.getStatus(GameServer.getInstance().recvKey)
                == Msg4Recv.Status.COMPLETE) {

            MsgBody body = recv.flush();
            body.decrypt(GameServer.getInstance().recvKey);

            GameServer.getInstance().enqueueIncoming(
                    new Msg2Handle(recv.getId(), body)
            );
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("Client connected: " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("Client disconnected: " + ctx.channel().remoteAddress());
        client.disconnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
