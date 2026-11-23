package de.brickforceaurora.server.net.tcp.protocol.serverbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ServerboundInstallGadgetPacket implements IServerboundPacket {

    private int gadget;
    private float px;
    private float py;
    private float pz;
    private float nx;
    private float ny;
    private float nz;

    public ServerboundInstallGadgetPacket gadget(final int gadget) {
        this.gadget = gadget;
        return this;
    }

    public int gadget() {
        return this.gadget;
    }

    public ServerboundInstallGadgetPacket px(final float px) {
        this.px = px;
        return this;
    }

    public float px() {
        return this.px;
    }

    public ServerboundInstallGadgetPacket py(final float py) {
        this.py = py;
        return this;
    }

    public float py() {
        return this.py;
    }

    public ServerboundInstallGadgetPacket pz(final float pz) {
        this.pz = pz;
        return this;
    }

    public float pz() {
        return this.pz;
    }

    public ServerboundInstallGadgetPacket nx(final float nx) {
        this.nx = nx;
        return this;
    }

    public float nx() {
        return this.nx;
    }

    public ServerboundInstallGadgetPacket ny(final float ny) {
        this.ny = ny;
        return this;
    }

    public float ny() {
        return this.ny;
    }

    public ServerboundInstallGadgetPacket nz(final float nz) {
        this.nz = nz;
        return this;
    }

    public float nz() {
        return this.nz;
    }

    @Override
    public int packetId() {
        return 400;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.gadget = buf.readInt();
        this.px = buf.readFloat();
        this.py = buf.readFloat();
        this.pz = buf.readFloat();
        this.nx = buf.readFloat();
        this.ny = buf.readFloat();
        this.nz = buf.readFloat();
    }
}