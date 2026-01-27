package de.brickforceaurora.gameserver.net.protocol.serverbound;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.io.FastByteArrayInputStream;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;

public final class ServerboundRegisterPacket implements IServerboundPacket {

	private int slot;
	private int modeMask;
	private int regHow;
	private int point;
	private int downloadFee;
	private BufferedImage thumbnail;
	private String msgEval;

	public final ServerboundRegisterPacket slot(int slot) {
		this.slot = slot;
		return this;
	}

	public final int slot() {
		return this.slot;
	}

	public final ServerboundRegisterPacket modeMask(int modeMask) {
		if (modeMask > 32767L || modeMask < 0L) {
			throw new IllegalArgumentException(
					"Value " + modeMask + " is out of bounds of allowed number range of 0 - 32767");
		}
		this.modeMask = modeMask;
		return this;
	}

	public final int modeMask() {
		return this.modeMask;
	}

	public final ServerboundRegisterPacket regHow(int regHow) {
		this.regHow = regHow;
		return this;
	}

	public final int regHow() {
		return this.regHow;
	}

	public final ServerboundRegisterPacket point(int point) {
		this.point = point;
		return this;
	}

	public final int point() {
		return this.point;
	}

	public final ServerboundRegisterPacket downloadFee(int downloadFee) {
		this.downloadFee = downloadFee;
		return this;
	}

	public final int downloadFee() {
		return this.downloadFee;
	}

    public final ServerboundRegisterPacket thumbnail(BufferedImage thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public final BufferedImage thumbnail() {
        return this.thumbnail;
    }

	public final ServerboundRegisterPacket msgEval(String msgEval) {
		this.msgEval = msgEval;
		return this;
	}

	public final String msgEval() {
		return this.msgEval;
	}

	@Override
	public int packetId() {
		return 51;
	}

	@Override
	public final void read(ByteBuf buffer) throws IOException {
		this.slot = buffer.readIntLE();
		this.modeMask = buffer.readUnsignedShortLE();
		this.regHow = buffer.readIntLE();
		this.point = buffer.readIntLE();
		this.downloadFee = buffer.readIntLE();
        {
            int length = buffer.readIntLE();
            if (length == 0) {
                this.thumbnail = null;
            } else {
                byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                FastByteArrayInputStream input = new FastByteArrayInputStream(bytes);
                this.thumbnail = ImageIO.read(input);
            }
        }
		{
			int length = buffer.readIntLE();
			if (length == 0) {
				this.msgEval = "";
			} else {
				byte[] bytes = new byte[length];
				buffer.readBytes(bytes);
				this.msgEval = new String(bytes, StandardCharsets.UTF_16LE);
			}
		}
	}
}