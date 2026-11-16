package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundSavePalettePacket implements IServerboundPacket {

    private int pal0;
    private int pal1;
    private int pal2;
    private int pal3;
    private int pal4;
    private int pal5;
    private int pal6;
    private int pal7;
    private int pal8;
    private int pal9;

    public ServerboundSavePalettePacket pal0(final int pal0) {
        this.pal0 = pal0;
        return this;
    }

    public int pal0() {
        return this.pal0;
    }

    public ServerboundSavePalettePacket pal1(final int pal1) {
        this.pal1 = pal1;
        return this;
    }

    public int pal1() {
        return this.pal1;
    }

    public ServerboundSavePalettePacket pal2(final int pal2) {
        this.pal2 = pal2;
        return this;
    }

    public int pal2() {
        return this.pal2;
    }

    public ServerboundSavePalettePacket pal3(final int pal3) {
        this.pal3 = pal3;
        return this;
    }

    public int pal3() {
        return this.pal3;
    }

    public ServerboundSavePalettePacket pal4(final int pal4) {
        this.pal4 = pal4;
        return this;
    }

    public int pal4() {
        return this.pal4;
    }

    public ServerboundSavePalettePacket pal5(final int pal5) {
        this.pal5 = pal5;
        return this;
    }

    public int pal5() {
        return this.pal5;
    }

    public ServerboundSavePalettePacket pal6(final int pal6) {
        this.pal6 = pal6;
        return this;
    }

    public int pal6() {
        return this.pal6;
    }

    public ServerboundSavePalettePacket pal7(final int pal7) {
        this.pal7 = pal7;
        return this;
    }

    public int pal7() {
        return this.pal7;
    }

    public ServerboundSavePalettePacket pal8(final int pal8) {
        this.pal8 = pal8;
        return this;
    }

    public int pal8() {
        return this.pal8;
    }

    public ServerboundSavePalettePacket pal9(final int pal9) {
        this.pal9 = pal9;
        return this;
    }

    public int pal9() {
        return this.pal9;
    }

    @Override
    public int packetId() {
        return 19;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.pal0 = buf.readInt();
        this.pal1 = buf.readInt();
        this.pal2 = buf.readInt();
        this.pal3 = buf.readInt();
        this.pal4 = buf.readInt();
        this.pal5 = buf.readInt();
        this.pal6 = buf.readInt();
        this.pal7 = buf.readInt();
        this.pal8 = buf.readInt();
        this.pal9 = buf.readInt();
    }
}