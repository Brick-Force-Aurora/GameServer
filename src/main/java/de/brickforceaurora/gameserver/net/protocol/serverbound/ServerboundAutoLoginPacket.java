package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

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
    public void read(final ByteBuf buffer) {
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.token = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.token = new String(bytes, StandardCharsets.UTF_16LE);
            }
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