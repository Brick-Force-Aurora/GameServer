package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

public final class ClientboundWeaponModifierExPacket implements IClientboundPacket {

    private int val;
    private int val2;
    private float val3;
    private float val4;
    private int val5;
    private float val6;
    private int val7;
    private float val8;
    private float val9;
    private float val10;
    private int val11;
    private int val12;
    private int val13;
    private float val14;
    private float val15;

    public ClientboundWeaponModifierExPacket val(final int val) {
        this.val = val;
        return this;
    }

    public int val() {
        return this.val;
    }

    public ClientboundWeaponModifierExPacket val2(final int val2) {
        this.val2 = val2;
        return this;
    }

    public int val2() {
        return this.val2;
    }

    public ClientboundWeaponModifierExPacket val3(final float val3) {
        this.val3 = val3;
        return this;
    }

    public float val3() {
        return this.val3;
    }

    public ClientboundWeaponModifierExPacket val4(final float val4) {
        this.val4 = val4;
        return this;
    }

    public float val4() {
        return this.val4;
    }

    public ClientboundWeaponModifierExPacket val5(final int val5) {
        this.val5 = val5;
        return this;
    }

    public int val5() {
        return this.val5;
    }

    public ClientboundWeaponModifierExPacket val6(final float val6) {
        this.val6 = val6;
        return this;
    }

    public float val6() {
        return this.val6;
    }

    public ClientboundWeaponModifierExPacket val7(final int val7) {
        this.val7 = val7;
        return this;
    }

    public int val7() {
        return this.val7;
    }

    public ClientboundWeaponModifierExPacket val8(final float val8) {
        this.val8 = val8;
        return this;
    }

    public float val8() {
        return this.val8;
    }

    public ClientboundWeaponModifierExPacket val9(final float val9) {
        this.val9 = val9;
        return this;
    }

    public float val9() {
        return this.val9;
    }

    public ClientboundWeaponModifierExPacket val10(final float val10) {
        this.val10 = val10;
        return this;
    }

    public float val10() {
        return this.val10;
    }

    public ClientboundWeaponModifierExPacket val11(final int val11) {
        this.val11 = val11;
        return this;
    }

    public int val11() {
        return this.val11;
    }

    public ClientboundWeaponModifierExPacket val12(final int val12) {
        this.val12 = val12;
        return this;
    }

    public int val12() {
        return this.val12;
    }

    public ClientboundWeaponModifierExPacket val13(final int val13) {
        this.val13 = val13;
        return this;
    }

    public int val13() {
        return this.val13;
    }

    public ClientboundWeaponModifierExPacket val14(final float val14) {
        this.val14 = val14;
        return this;
    }

    public float val14() {
        return this.val14;
    }

    public ClientboundWeaponModifierExPacket val15(final float val15) {
        this.val15 = val15;
        return this;
    }

    public float val15() {
        return this.val15;
    }

    @Override
    public int packetId() {
        return 452;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.val);
        buf.writeInt(this.val2);
        buf.writeFloat(this.val3);
        buf.writeFloat(this.val4);
        buf.writeInt(this.val5);
        buf.writeFloat(this.val6);
        buf.writeInt(this.val7);
        buf.writeFloat(this.val8);
        buf.writeFloat(this.val9);
        buf.writeFloat(this.val10);
        buf.writeInt(this.val11);
        buf.writeInt(this.val12);
        buf.writeInt(this.val13);
        buf.writeFloat(this.val14);
        buf.writeFloat(this.val15);
    }
}