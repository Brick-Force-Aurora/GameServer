package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundAutoLoginToNetmarblePacket implements IServerboundPacket {

    final String UnknownValue0 = "CPCookie[i]";
    private int major;
    private int minor;
    private String privateIpAddress;
    private String macAddress;

    public ServerboundAutoLoginToNetmarblePacket major(final int major) {
        this.major = major;
        return this;
    }

    public int major() {
        return this.major;
    }

    public ServerboundAutoLoginToNetmarblePacket minor(final int minor) {
        this.minor = minor;
        return this;
    }

    public int minor() {
        return this.minor;
    }

    public ServerboundAutoLoginToNetmarblePacket privateIpAddress(final String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
        return this;
    }

    public String privateIpAddress() {
        return this.privateIpAddress;
    }

    public ServerboundAutoLoginToNetmarblePacket macAddress(final String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public String macAddress() {
        return this.macAddress;
    }

    @Override
    public int packetId() {
        return 458;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.major = buf.readInt();
        this.minor = buf.readInt();
        this.privateIpAddress = buf.readString();
        this.macAddress = buf.readString();
    }
}