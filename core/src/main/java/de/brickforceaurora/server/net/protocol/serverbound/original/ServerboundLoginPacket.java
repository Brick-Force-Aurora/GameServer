package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundLoginPacket implements IServerboundPacket {

    private String id;
    private String pswd;
    private int major;
    private int minor;
    private String privateIpAddress;
    private String macAddress;

    public ServerboundLoginPacket id(final String id) {
        this.id = id;
        return this;
    }

    public String id() {
        return this.id;
    }

    public ServerboundLoginPacket pswd(final String pswd) {
        this.pswd = pswd;
        return this;
    }

    public String pswd() {
        return this.pswd;
    }

    public ServerboundLoginPacket major(final int major) {
        this.major = major;
        return this;
    }

    public int major() {
        return this.major;
    }

    public ServerboundLoginPacket minor(final int minor) {
        this.minor = minor;
        return this;
    }

    public int minor() {
        return this.minor;
    }

    public ServerboundLoginPacket privateIpAddress(final String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
        return this;
    }

    public String privateIpAddress() {
        return this.privateIpAddress;
    }

    public ServerboundLoginPacket macAddress(final String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public String macAddress() {
        return this.macAddress;
    }

    @Override
    public int packetId() {
        return 1;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.id = buf.readString();
        this.pswd = buf.readString();
        this.major = buf.readInt();
        this.minor = buf.readInt();
        this.privateIpAddress = buf.readString();
        this.macAddress = buf.readString();
    }
}