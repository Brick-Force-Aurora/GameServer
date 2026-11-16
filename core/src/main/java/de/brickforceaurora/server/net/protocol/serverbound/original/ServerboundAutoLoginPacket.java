package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAutoLoginPacket implements IServerboundPacket {

    private String token;
    private int major;
    private int minor;
    private String privateIpAddress;
    private String macAddress;

    public ServerboundAutoLoginPacket token(final String token) {
        this.token = token;
        return this;
    }

    public String token() {
        return this.token;
    }

    public ServerboundAutoLoginPacket major(final int major) {
        this.major = major;
        return this;
    }

    public int major() {
        return this.major;
    }

    public ServerboundAutoLoginPacket minor(final int minor) {
        this.minor = minor;
        return this;
    }

    public int minor() {
        return this.minor;
    }

    public ServerboundAutoLoginPacket privateIpAddress(final String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
        return this;
    }

    public String privateIpAddress() {
        return this.privateIpAddress;
    }

    public ServerboundAutoLoginPacket macAddress(final String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public String macAddress() {
        return this.macAddress;
    }

    @Override
    public int packetId() {
        return 393;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.token = buf.readString();
        this.major = buf.readInt();
        this.minor = buf.readInt();
        this.privateIpAddress = buf.readString();
        this.macAddress = buf.readString();
    }
}