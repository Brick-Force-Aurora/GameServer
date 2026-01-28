package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.localIp = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.localIp = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.localPort = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.remoteIp = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.remoteIp = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.remotePort = buffer.readIntLE();
    }
}