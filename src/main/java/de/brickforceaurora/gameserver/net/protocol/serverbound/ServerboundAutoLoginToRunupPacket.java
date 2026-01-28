package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.id = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.id = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.serial = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.serial = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.clientId = buffer.readIntLE();
        this.major = buffer.readIntLE();
        this.minor = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.privateIpAddress = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.privateIpAddress = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.macAddress = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.macAddress = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}