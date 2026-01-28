package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundCustomStringPacket implements IClientboundPacket {

    private String val;
    private String val2;
    private String val3;
    private String val4;
    private String val5;
    private String val6;
    private String val7;
    private String val8;
    private String val9;
    private String val10;
    private String val11;
    private String val12;
    private String val13;

    public ClientboundCustomStringPacket val(final String val) {
        this.val = val;
        return this;
    }

    public String val() {
        return this.val;
    }

    public ClientboundCustomStringPacket val2(final String val2) {
        this.val2 = val2;
        return this;
    }

    public String val2() {
        return this.val2;
    }

    public ClientboundCustomStringPacket val3(final String val3) {
        this.val3 = val3;
        return this;
    }

    public String val3() {
        return this.val3;
    }

    public ClientboundCustomStringPacket val4(final String val4) {
        this.val4 = val4;
        return this;
    }

    public String val4() {
        return this.val4;
    }

    public ClientboundCustomStringPacket val5(final String val5) {
        this.val5 = val5;
        return this;
    }

    public String val5() {
        return this.val5;
    }

    public ClientboundCustomStringPacket val6(final String val6) {
        this.val6 = val6;
        return this;
    }

    public String val6() {
        return this.val6;
    }

    public ClientboundCustomStringPacket val7(final String val7) {
        this.val7 = val7;
        return this;
    }

    public String val7() {
        return this.val7;
    }

    public ClientboundCustomStringPacket val8(final String val8) {
        this.val8 = val8;
        return this;
    }

    public String val8() {
        return this.val8;
    }

    public ClientboundCustomStringPacket val9(final String val9) {
        this.val9 = val9;
        return this;
    }

    public String val9() {
        return this.val9;
    }

    public ClientboundCustomStringPacket val10(final String val10) {
        this.val10 = val10;
        return this;
    }

    public String val10() {
        return this.val10;
    }

    public ClientboundCustomStringPacket val11(final String val11) {
        this.val11 = val11;
        return this;
    }

    public String val11() {
        return this.val11;
    }

    public ClientboundCustomStringPacket val12(final String val12) {
        this.val12 = val12;
        return this;
    }

    public String val12() {
        return this.val12;
    }

    public ClientboundCustomStringPacket val13(final String val13) {
        this.val13 = val13;
        return this;
    }

    public String val13() {
        return this.val13;
    }

    @Override
    public int packetId() {
        return 422;
    }

    @Override
    public void write(final ByteBuf buffer) {
        if (this.val.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val2.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val2.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val3.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val3.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val4.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val4.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val5.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val5.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val6.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val6.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val7.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val7.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val8.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val8.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val9.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val9.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val10.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val10.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val11.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val11.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val12.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val12.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        if (this.val13.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.val13.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
    }
}