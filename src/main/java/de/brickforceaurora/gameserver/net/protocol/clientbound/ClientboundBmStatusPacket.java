package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundBmStatusPacket implements IClientboundPacket {

	private boolean rounding;
	private int bombInstaller;
	private int blastTarget;
	private float posX;
	private float posY;
	private float posZ;
	private float normalX;
    private float normalY;
    private float normalZ;

	public final ClientboundBmStatusPacket rounding(boolean rounding) {
		this.rounding = rounding;
		return this;
	}

	public final boolean rounding() {
		return this.rounding;
	}

	public final ClientboundBmStatusPacket bombInstaller(int bombInstaller) {
		this.bombInstaller = bombInstaller;
		return this;
	}

	public final int bombInstaller() {
		return this.bombInstaller;
	}

	public final ClientboundBmStatusPacket blastTarget(int blastTarget) {
		this.blastTarget = blastTarget;
		return this;
	}

	public final int blastTarget() {
		return this.blastTarget;
	}

    public final ClientboundBmStatusPacket posX(float posX) {
        this.posX = posX;
        return this;
    }

    public final float posX() {
        return this.posX;
    }

    public final ClientboundBmStatusPacket posY(float posY) {
        this.posY = posY;
        return this;
    }

    public final float posY() {
        return this.posY;
    }

    public final ClientboundBmStatusPacket posZ(float posZ) {
        this.posZ = posZ;
        return this;
    }

    public final float posZ() {
        return this.posZ;
    }

	public final ClientboundBmStatusPacket normalX(float normalX) {
		this.normalX = normalX;
		return this;
	}

	public final float normalX() {
		return this.normalX;
	}

	public final ClientboundBmStatusPacket normalY(float normalY) {
		this.normalY = normalY;
		return this;
	}

	public final float normalY() {
		return this.normalY;
	}

	public final ClientboundBmStatusPacket normalZ(float normalZ) {
		this.normalZ = normalZ;
		return this;
	}

	public final float normalZ() {
		return this.normalZ;
	}

	@Override
	public int packetId() {
		return 301;
	}

	@Override
	public final void write(ByteBuf buffer) {
		buffer.writeBoolean(this.rounding);
		buffer.writeIntLE(this.bombInstaller);
		buffer.writeIntLE(this.blastTarget);
		buffer.writeFloatLE(this.posX);
		buffer.writeFloatLE(this.posY);
		buffer.writeFloatLE(this.posZ);
        buffer.writeFloatLE(this.normalX);
        buffer.writeFloatLE(this.normalY);
        buffer.writeFloatLE(this.normalZ);
	}
}