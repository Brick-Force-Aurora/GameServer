package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundRandezvousPointPacket implements IServerboundPacket {

    private String localIp;
    private int localPort;
    private String remoteIp;
    private int remotePort;

    public ServerboundRandezvousPointPacket localIp(final String localIp) {
        this.localIp = localIp;
        return this;
    }

    public String localIp() {
        return this.localIp;
    }

    public ServerboundRandezvousPointPacket localPort(final int localPort) {
        this.localPort = localPort;
        return this;
    }

    public int localPort() {
        return this.localPort;
    }

    public ServerboundRandezvousPointPacket remoteIp(final String remoteIp) {
        this.remoteIp = remoteIp;
        return this;
    }

    public String remoteIp() {
        return this.remoteIp;
    }

    public ServerboundRandezvousPointPacket remotePort(final int remotePort) {
        this.remotePort = remotePort;
        return this;
    }

    public int remotePort() {
        return this.remotePort;
    }

    @Override
    public int packetId() {
        return 83;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.localIp = buf.readString();
        this.localPort = buf.readInt();
        this.remoteIp = buf.readString();
        this.remotePort = buf.readInt();
    }
}