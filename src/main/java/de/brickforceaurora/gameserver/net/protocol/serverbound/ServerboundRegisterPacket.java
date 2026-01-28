package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;

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
        if (modeMask > 32767L || modeMask < 0L) {
            throw new IllegalArgumentException("Value " + modeMask + " is out of bounds of allowed number range of 0 - 32767");
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
    public void read(final ByteBuf buffer) throws IOException {
        this.slot = buffer.readIntLE();
        this.modeMask = buffer.readUnsignedShortLE();
        this.regHow = buffer.readIntLE();
        this.point = buffer.readIntLE();
        this.downloadFee = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.thumbnail = null;
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                final FastByteArrayInputStream input = new FastByteArrayInputStream(bytes);
                this.thumbnail = ImageIO.read(input);
            }
        }
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.msgEval = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.msgEval = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
    }
}