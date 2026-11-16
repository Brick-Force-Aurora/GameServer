package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundRendezvousPointPacket implements IClientboundPacket {

    private int clientId;
    private String localIp;
    private int localPort;
    private String remoteIp;
    private int remotePort;

    public ClientboundRendezvousPointPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ClientboundRendezvousPointPacket localIp(final String localIp) {
        this.localIp = localIp;
        return this;
    }

    public String localIp() {
        return this.localIp;
    }

    public ClientboundRendezvousPointPacket localPort(final int localPort) {
        this.localPort = localPort;
        return this;
    }

    public int localPort() {
        return this.localPort;
    }

    public ClientboundRendezvousPointPacket remoteIp(final String remoteIp) {
        this.remoteIp = remoteIp;
        return this;
    }

    public String remoteIp() {
        return this.remoteIp;
    }

    public ClientboundRendezvousPointPacket remotePort(final int remotePort) {
        this.remotePort = remotePort;
        return this;
    }

    public int remotePort() {
        return this.remotePort;
    }

    @Override
    public int packetId() {
        return 153;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.clientId);
        buf.writeString(this.localIp);
        buf.writeInt(this.localPort);
        buf.writeString(this.remoteIp);
        buf.writeInt(this.remotePort);
    }
}