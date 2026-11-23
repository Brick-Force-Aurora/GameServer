package de.brickforceaurora.server.net.udp;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Set;

import de.brickforceaurora.server.ServerApp;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.udp.RelayStats.Event;
import de.brickforceaurora.server.net.udp.protocol.P2PHeader;
import de.brickforceaurora.server.net.udp.protocol.P2PMessage;
import de.brickforceaurora.server.net.udp.protocol.clientbound.ClientboundP2PPongPacket;
import de.brickforceaurora.server.net.udp.protocol.serverbound.ServerboundP2PPingPacket;
import de.brickforceaurora.server.net.udp.protocol.serverbound.ServerboundP2PReliableAckPacket;
import de.brickforceaurora.server.net.udp.protocol.serverbound.ServerboundP2PStatusPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

final class RendezvousHandler<S extends ServerApp<S>> extends SimpleChannelInboundHandler<P2PMessage> {

    private final RendezvousManager<S> server;

    RendezvousHandler(final RendezvousManager<S> server) {
        this.server = server;
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext context, final P2PMessage message) {
        if (message.packet() instanceof final ServerboundP2PPingPacket ping) {
            // The client isn't known by its endpoint yet
            onPing(context, message.sender(), ping);
            return;
        }
        final BFClient client = server.clientAt(message.sender());
        if (client == null) {
            // we don't relay for someone unkown
            server.stats().count(Event.UNKNOWN_SENDER);
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
        final BFClient client = server.snowFrame().app().server().netManager().clientById(ping.clientId()).orElse(null);
        // only accept pings from the address of the client
        if (client == null || !sender.getAddress().getHostAddress().equals(client.ip())) {
            server.stats().count(Event.REJECTED_PING);
            return;
        }
        server.register(client, sender);
        context.writeAndFlush(RendezvousManager.pong(sender, sender.getAddress().getHostAddress(), sender.getPort(), ping.time()));
    }

    private void onStatus(final BFClient client, final ServerboundP2PStatusPacket status) {
        client.attrSet(RendezvousManager.ATTR_RELAYED, Set.copyOf(status.relayedPeers()));
    }

    private void relay(final ChannelHandlerContext context, final BFClient sender, final P2PMessage message) {
        final List<BFClient> peers = server.directory().peersOf(sender);
        if (peers.isEmpty()) {
            server.stats().count(Event.NO_PEERS);
            return;
        }
        final P2PHeader header = message.header();
        final ByteBuf data = message.content();
        final long now = System.nanoTime();

        if (message.packet() instanceof final ServerboundP2PReliableAckPacket ack) {
            relayAck(context, sender, peers, ack, data, now);
            return;
        }

        final boolean reliable = header.reliable();
        final boolean broadcast = header.broadcast();
        final Set<Integer> relayed = broadcast && !reliable ? relayedPeers(sender) : Set.of();
        boolean forwarded = false;
        for (final BFClient peer : peers) {
            final boolean wanted;
            if (broadcast) {
                wanted = reliable || relayed.contains(peer.id());
            } else {
                wanted = server.directory().slotOf(peer) == header.destination();
            }
            if (!wanted) {
                continue;
            }
            if (reliable) {
                server.routes().forwarded(sender.id(), peer.id(), header.meta(), now);
            }
            forwarded |= forward(context, peer, data);
        }
        if (!forwarded) {
            server.stats().count(broadcast ? Event.NOT_RELAYED : Event.NO_DESTINATION);
        }
    }

    private void relayAck(final ChannelHandlerContext context, final BFClient sender, final List<BFClient> peers,
        final ServerboundP2PReliableAckPacket ack, final ByteBuf data, final long now) {
        final ReliableRoutes.Resolution resolution = server.routes().acknowledged(sender.id(), ack.meta(), now).orElse(null);
        if (resolution == null) {
            server.stats().count(Event.UNMATCHED_ACK);
            return;
        }
        if (resolution.ambiguous()) {
            server.stats().count(Event.AMBIGUOUS_ACK);
        }
        for (final BFClient peer : peers) {
            if (peer.id() == resolution.origin()) {
                forward(context, peer, data);
                return;
            }
        }
        // The origin left the room
        server.stats().count(Event.UNMATCHED_ACK);
    }

    private boolean forward(final ChannelHandlerContext context, final BFClient target, final ByteBuf data) {
        final InetSocketAddress endpoint = RendezvousManager.observed(target).orElse(null);
        if (endpoint == null) {
            server.stats().count(Event.NO_ENDPOINT);
            return false;
        }
        context.writeAndFlush(new DatagramPacket(data.retainedDuplicate(), endpoint));
        return true;
    }

    private Set<Integer> relayedPeers(final BFClient client) {
        final Set<Integer> relayed = client.attr(RendezvousManager.ATTR_RELAYED, Set.class);
        if (relayed == null && server.firstStatusWarning(client)) {
            server.logger().warning("Client '{0}' broadcasts to the relay but never reported which peers need it, its broadcasts are dropped",
                client.name());
        }
        return relayed == null ? Set.of() : relayed;
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext context, final Throwable cause) {
        server.logger().error("Rendezvous error", cause);
    }

}
