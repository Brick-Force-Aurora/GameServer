package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundAutoLoginToNetmarblePacket implements IServerboundPacket {

    private byte[] cpCookie;
    private int major;
    private int minor;
    private String privateIpAddress;
    private String macAddress;

    public ServerboundAutoLoginToNetmarblePacket cpCookie(final byte[] cpCookie) {
        this.cpCookie = cpCookie;
        return this;
    }

    public byte[] cpCookie() {
        return this.cpCookie;
    }

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
    public void read(final ByteBuf buffer) {
        {
            // TODO: NO IDEA HOW TO READ cpCookie
        }
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