package de.brickforceaurora.server.net.protocol.serverbound.original;

import java.awt.image.BufferedImage;
import java.io.IOException;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ServerboundRegisterPacket implements IServerboundPacket {

    private int slot;
    private int modeMask;
    private int regHow;
    private int point;
    private int downloadFee;
    private BufferedImage thumbnail;
    private String msgEval;

    public ServerboundRegisterPacket slot(final int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return this.slot;
    }

    public ServerboundRegisterPacket modeMask(final int modeMask) {
        if (modeMask > 65535L || modeMask < 0L) {
            throw new IllegalArgumentException("Value " + modeMask + " is out of bounds of allowed number range of 0 - 65535");
        }
        this.modeMask = modeMask;
        return this;
    }

    public int modeMask() {
        return this.modeMask;
    }

    public ServerboundRegisterPacket regHow(final int regHow) {
        this.regHow = regHow;
        return this;
    }

    public int regHow() {
        return this.regHow;
    }

    public ServerboundRegisterPacket point(final int point) {
        this.point = point;
        return this;
    }

    public int point() {
        return this.point;
    }

    public ServerboundRegisterPacket downloadFee(final int downloadFee) {
        this.downloadFee = downloadFee;
        return this;
    }

    public int downloadFee() {
        return this.downloadFee;
    }

    public ServerboundRegisterPacket thumbnail(final BufferedImage thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public BufferedImage thumbnail() {
        return this.thumbnail;
    }

    public ServerboundRegisterPacket msgEval(final String msgEval) {
        this.msgEval = msgEval;
        return this;
    }

    public String msgEval() {
        return this.msgEval;
    }

    @Override
    public int packetId() {
        return 51;
    }

    @Override
    public void read(final PacketBuf buf) throws IOException {
        this.slot = buf.readInt();
        this.modeMask = buf.readUnsignedShort();
        this.regHow = buf.readInt();
        this.point = buf.readInt();
        this.downloadFee = buf.readInt();
        this.thumbnail = buf.readImage();
        this.msgEval = buf.readString();
    }
}