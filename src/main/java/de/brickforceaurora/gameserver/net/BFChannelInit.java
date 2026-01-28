package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.BFServerboundPacketDecoder;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundSeedPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.emulator.ClientboundEmulatorConnectedPacket;
import de.brickforceaurora.gameserver.net.protocol.BFClientboundPacketEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

final class BFChannelInit extends ChannelInitializer<SocketChannel> {
    
    private static final ClientboundEmulatorConnectedPacket CONNECTED = new ClientboundEmulatorConnectedPacket();
    private static final ClientboundSeedPacket SEED = new ClientboundSeedPacket().seed(255);

    private final NetManager netManager;

    public BFChannelInit(NetManager netManager) {
        this.netManager = netManager;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        BFClient client = new BFClient(channel);

        ChannelPipeline pipeline = channel.pipeline();
        // Inbound
        pipeline.addLast(new BFServerboundPacketDecoder());
        pipeline.addLast(new BFServerboundPacketListener(netManager, client));
        // Outbound
        // pipeline.addLast(new BFClientboundPacketListener(netManager, client)); // We don't need this right now so its commented out
        pipeline.addLast(new BFClientboundPacketEncoder(client));

        netManager.clientConnected(client);
        
        channel.writeAndFlush(CONNECTED);
        channel.writeAndFlush(SEED);
    }

}
