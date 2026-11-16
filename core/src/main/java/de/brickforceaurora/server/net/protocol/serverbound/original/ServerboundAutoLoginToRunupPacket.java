package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundAutoLoginToRunupPacket implements IServerboundPacket {

    private String id;
    private String serial;
    private int clientId;
    private int major;
    private int minor;
    private String privateIpAddress;
    private String macAddress;

    public ServerboundAutoLoginToRunupPacket id(final String id) {
        this.id = id;
        return this;
    }

    public String id() {
        return this.id;
    }

    public ServerboundAutoLoginToRunupPacket serial(final String serial) {
        this.serial = serial;
        return this;
    }

    public String serial() {
        return this.serial;
    }

    public ServerboundAutoLoginToRunupPacket clientId(final int clientId) {
        this.clientId = clientId;
        return this;
    }

    public int clientId() {
        return this.clientId;
    }

    public ServerboundAutoLoginToRunupPacket major(final int major) {
        this.major = major;
        return this;
    }

    public int major() {
        return this.major;
    }

    public ServerboundAutoLoginToRunupPacket minor(final int minor) {
        this.minor = minor;
        return this;
    }

    public int minor() {
        return this.minor;
    }

    public ServerboundAutoLoginToRunupPacket privateIpAddress(final String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
        return this;
    }

    public String privateIpAddress() {
        return this.privateIpAddress;
    }

    public ServerboundAutoLoginToRunupPacket macAddress(final String macAddress) {
        this.macAddress = macAddress;
        return this;
    }

    public String macAddress() {
        return this.macAddress;
    }

    @Override
    public int packetId() {
        return 409;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.id = buf.readString();
        this.serial = buf.readString();
        this.clientId = buf.readInt();
        this.major = buf.readInt();
        this.minor = buf.readInt();
        this.privateIpAddress = buf.readString();
        this.macAddress = buf.readString();
    }
}