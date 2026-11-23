package de.brickforceaurora.server.net.udp.protocol.clientbound;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundP2PPongPacket implements IClientboundPacket {

    private String ip;
    private int port;
    private float time;
    private long tick;

    @Override
    public int packetId() {
        return 25;
    }

    public String ip() {
        return ip;
    }

    public ClientboundP2PPongPacket ip(final String ip) {
        this.ip = ip == null ? "" : ip;
        return this;
    }

    public int port() {
        return port;
    }

    public ClientboundP2PPongPacket port(final int port) {
        this.port = port;
        return this;
    }

    public float time() {
        return time;
    }

    public ClientboundP2PPongPacket time(final float time) {
        this.time = time;
        return this;
    }

    public long tick() {
        return tick;
    }

    public ClientboundP2PPongPacket tick(final long tick) {
        this.tick = tick;
        return this;
    }

    @Override
    public void write(final PacketBuf buffer) {
        buffer.writeString(ip);
        buffer.writeInt(port);
        buffer.writeFloat(time);
        buffer.writeLong(tick);
    }

}
