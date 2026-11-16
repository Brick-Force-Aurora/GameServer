package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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
    public void write(final PacketBuf buf) {
        buf.writeString(this.val);
        buf.writeString(this.val2);
        buf.writeString(this.val3);
        buf.writeString(this.val4);
        buf.writeString(this.val5);
        buf.writeString(this.val6);
        buf.writeString(this.val7);
        buf.writeString(this.val8);
        buf.writeString(this.val9);
        buf.writeString(this.val10);
        buf.writeString(this.val11);
        buf.writeString(this.val12);
        buf.writeString(this.val13);
    }
}