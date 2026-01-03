package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.GameServerApp;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SnowFrame;

public final class NetManager extends ChannelInitializer<SocketChannel> implements AutoCloseable {

    private final NioEventLoopGroup mainGroup = new NioEventLoopGroup(1);
    private final NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    
    private final ServerBootstrap bootstrap = new ServerBootstrap();
    private volatile Channel serverChannel;
    
    private final SnowFrame<GameServerApp> frame;
    private final ISimpleLogger logger;
    
    public NetManager(SnowFrame<GameServerApp> frame) {
        this.frame = frame;
        this.logger = frame.logger();
    }
    
    public void open() {
        bootstrap.group(mainGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(this);
    }

    @Override
    public void close() throws Exception {
        if (serverChannel != null) {
            serverChannel.close().awaitUninterruptibly();
            serverChannel = null;
        }
        mainGroup.close();
        workerGroup.close();
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        
    }

}
