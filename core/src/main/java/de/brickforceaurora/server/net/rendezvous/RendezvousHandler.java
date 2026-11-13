package de.brickforceaurora.server.net.rendezvous;

import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.rendezvous.protocol.P2PHeader;
import de.brickforceaurora.server.net.rendezvous.protocol.clientbound.ClientboundP2PPongPacket;
import de.brickforceaurora.server.net.rendezvous.protocol.serverbound.ServerboundP2PPingPacket;
import de.brickforceaurora.server.net.rendezvous.protocol.serverbound.ServerboundP2PMessage;
import de.brickforceaurora.server.net.rendezvous.protocol.serverbound.ServerboundP2PReliableAckPacket;
import de.brickforceaurora.server.net.rendezvous.protocol.serverbound.ServerboundP2PStatusPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;


final class RendezvousHandler extends SimpleChannelInboundHandler<ServerboundP2PMessage> {

    private static final int MAX_PENDING_ACKS = 4096;

    private final RendezvousServer server;

    private final Map<Long, Integer> pendingAcks = new LinkedHashMap<>() {

        private static final long serialVersionUID = -3419695974421713317L;

        @Override
        protected boolean removeEldestEntry(final Map.Entry<Long, Integer> eldest) {
            return size() > MAX_PENDING_ACKS;
        }

    };

    RendezvousHandler(final RendezvousServer server) {
        this.server = server;
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext context, final ServerboundP2PMessage message) {
        if (message.packet() instanceof final ServerboundP2PPingPacket ping) {
            // The client isn't known by its endpoint yet
            onPing(context, message.sender(), ping);
            return;
        }
        final BFClient client = server.clientAt(message.sender());
        if (client == null) {
            // we don't relay for someone unkown
            return;
        }
        switch (message.packet()) {
        case final ServerboundP2PStatusPacket status -> onStatus(client, status);
        case final ClientboundP2PPongPacket _ -> {
        }
        case null, default -> relay(context, client, message);
        }
    }

    private void onPing(final ChannelHandlerContext context, final InetSocketAddress sender, final ServerboundP2PPingPacket ping) {
        final BFClient client = server.netManager().clientById(ping.clientId()).orElse(null);
        // only accept pings from the address of the client
        if (client == null || !sender.getAddress().getHostAddress().equals(client.ip())) {
            return;
        }
        server.register(client, sender);
        context.writeAndFlush(RendezvousServer.pong(sender, sender.getAddress().getHostAddress(), sender.getPort(), ping.time()));
    }

    private void onStatus(final BFClient client, final ServerboundP2PStatusPacket status) {
        client.attrSet(RendezvousServer.ATTR_RELAYED, Set.copyOf(status.relayedPeers()));
    }

    private void relay(final ChannelHandlerContext context, final BFClient sender, final ServerboundP2PMessage message) {
        final List<BFClient> peers = server.directory().peersOf(sender);
        if (peers.isEmpty()) {
            return;
        }
        final P2PHeader header = message.header();
        final ByteBuf data = message.content();
        final int meta = header.meta();
        final int destination = header.destination();

        if (message.packet() instanceof final ServerboundP2PReliableAckPacket ack) {
            final Integer origin = pendingAcks.remove(ackKey(sender.id(), ack.meta()));
            if (origin == null) {
                return;
            }
            for (final BFClient peer : peers) {
                if (peer.id() == origin) {
                    forward(context, peer, data);
                    break;
                }
            }
            return;
        }

        final boolean reliable = header.reliable();
        final boolean broadcast = header.broadcast();
        final Set<Integer> relayed = broadcast && !reliable ? relayedPeers(sender) : Set.of();
        for (final BFClient peer : peers) {
            final boolean wanted;
            if (broadcast) {
                wanted = reliable || relayed.contains(peer.id());
            } else {
                wanted = server.directory().slotOf(peer) == destination;
            }
            if (!wanted) {
                continue;
            }
            if (reliable) {
                pendingAcks.put(ackKey(peer.id(), meta), sender.id());
            }
            forward(context, peer, data);
        }
    }

    private void forward(final ChannelHandlerContext context, final BFClient target, final ByteBuf data) {
        final InetSocketAddress endpoint = RendezvousServer.observed(target).orElse(null);
        if (endpoint == null) {
            return;
        }
        context.writeAndFlush(new DatagramPacket(data.retainedDuplicate(), endpoint));
    }

    private long ackKey(final int clientId, final int meta) {
        return ((long) clientId << 16) | (meta & 0xFFFF);
    }

    private Set<Integer> relayedPeers(final BFClient client) {
        final Set<Integer> relayed = client.attr(RendezvousServer.ATTR_RELAYED, Set.class);
        return relayed == null ? Set.of() : relayed;
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
        server.logger().error("Rendezvous error", cause);
    }

}
