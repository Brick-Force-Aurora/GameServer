package de.brickforceaurora.gameserver.channel;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.protocol.MessageId;
import de.brickforceaurora.gameserver.protocol.Msg2Handle;
import de.brickforceaurora.gameserver.protocol.Msg4Recv;
import de.brickforceaurora.gameserver.protocol.MsgBody;
import de.brickforceaurora.gameserver.protocol.MsgReference;
import de.brickforceaurora.gameserver.protocol.SendType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public final class ReceiveHandler extends ChannelInboundHandlerAdapter {

    private final ClientReference client;
    private final Msg4Recv accumulator = new Msg4Recv();
    
    private final GameServerLogic logic = GameServerApp.get().server().logic();

    public ReceiveHandler(ClientReference client) {
        this.client = client;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msgObj) {
        ByteBuf in = (ByteBuf) msgObj;
        try {
            while (in.isReadable()) {
                // read as much as available into accumulator buffer
                int readable = in.readableBytes();
                byte[] tmp = new byte[readable];
                in.readBytes(tmp);

                accumulator.append(tmp);

                // flush ALL complete packets from accumulator
                Msg4Recv.Status status;
                while ((status = accumulator.getStatus(logic.recvKey)) == Msg4Recv.Status.COMPLETE) {
                    int id = accumulator.getId();
                    MsgBody body = accumulator.flush();
                    body.decrypt(logic.recvKey);
                    
                    MessageId mId = MessageId.fromId(id);
                    if (mId == null) {
                        
                        client.Disconnect(true);
                        ctx.close();
                        return;
                    }

                    logic.enqueueIncoming(
                            new MsgReference(
                                    mId,
                                    body,
                                    client,
                                    SendType.UNICAST,
                                    client.channel,
                                    client.matchData
                            )
                    );
                }

                if (status == Msg4Recv.Status.INVALID || status == Msg4Recv.Status.OVERFLOW) {
                    client.Disconnect(true);
                    ctx.close();
                    return;
                }
            }
        } finally {
            in.release();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logic.logger().info("Client connected: {0}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logic.logger().info("Client disconnected: {0}", ctx.channel().remoteAddress());
        client.Disconnect();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logic.logger().error("Exception occured during message read, disconnecting client.", cause);
        ctx.close();
    }
}
