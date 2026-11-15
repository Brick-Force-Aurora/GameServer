package de.brickforceaurora.server.match.room;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.rendezvous.RendezvousManager;


public record PeerEndpoints(String localIp, int localPort, String remoteIp, int remotePort) {

    public static final String ATTR_ENDPOINTS = "PeerEndpoints";

    public static final PeerEndpoints HIDDEN = new PeerEndpoints("0.0.0.0", 0, "0.0.0.0", 0);

    private static final Pattern IPV4 = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3}");

    public static PeerEndpoints of(final BFClient client) {
        final PeerEndpoints endpoints = client.attr(ATTR_ENDPOINTS, PeerEndpoints.class);
        if (endpoints != null) {
            return endpoints;
        }
        return new PeerEndpoints(client.ip(), client.port(), client.ip(), client.port());
    }


    public static PeerEndpoints validated(final BFClient client, final String localIp, final int localPort, final String remoteIp,
        final int remotePort) {
        final PeerEndpoints fallback = new PeerEndpoints(client.ip(), client.port(), client.ip(), client.port());

        String validRemoteIp = fallback.remoteIp;
        int validRemotePort = fallback.remotePort;
        final InetSocketAddress observed = RendezvousManager.observed(client).orElse(null);
        if (observed != null) {
            // What we saw is always more trustworthy than what the client claims
            validRemoteIp = observed.getAddress().getHostAddress();
            validRemotePort = observed.getPort();
        } else if (client.ip().equals(remoteIp) && isPort(remotePort)) {
            validRemotePort = remotePort;
        }

        String validLocalIp = fallback.localIp;
        int validLocalPort = fallback.localPort;
        if (isPrivateIp(localIp) && isPort(localPort)) {
            validLocalIp = localIp;
            validLocalPort = localPort;
        }
        return new PeerEndpoints(validLocalIp, validLocalPort, validRemoteIp, validRemotePort);
    }

    private static boolean isPort(final int port) {
        return port > 0 && port <= 0xFFFF;
    }

    private static boolean isPrivateIp(final String ip) {
        // Only literals, we don't want to do any dns lookups for what a client sends
        if (ip == null || !IPV4.matcher(ip).matches()) {
            return false;
        }
        try {
            final InetAddress address = InetAddress.getByName(ip);
            return address.isSiteLocalAddress() || address.isLoopbackAddress() || address.isLinkLocalAddress();
        } catch (final UnknownHostException exception) {
            return false;
        }
    }

}
