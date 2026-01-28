package de.brickforceaurora.gameserver.net.protocol.serverbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IServerboundPacket;
import io.netty.buffer.ByteBuf;

public final class ServerboundRoomConfigPacket implements IServerboundPacket {

    private int killCount;
    private int timeLimit;
    private int weaponOption;
    private int nWhere;
    private int breakInto;
    private int teamBalance;
    private int useBuildGun;
    private int itemPickup;
    private String whereAlias;
    private String pswd;
    private int type;

    public ServerboundRoomConfigPacket killCount(final int killCount) {
        this.killCount = killCount;
        return this;
    }

    public int killCount() {
        return this.killCount;
    }

    public ServerboundRoomConfigPacket timeLimit(final int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public int timeLimit() {
        return this.timeLimit;
    }

    public ServerboundRoomConfigPacket weaponOption(final int weaponOption) {
        this.weaponOption = weaponOption;
        return this;
    }

    public int weaponOption() {
        return this.weaponOption;
    }

    public ServerboundRoomConfigPacket nWhere(final int nWhere) {
        this.nWhere = nWhere;
        return this;
    }

    public int nWhere() {
        return this.nWhere;
    }

    public ServerboundRoomConfigPacket breakInto(final int breakInto) {
        this.breakInto = breakInto;
        return this;
    }

    public int breakInto() {
        return this.breakInto;
    }

    public ServerboundRoomConfigPacket teamBalance(final int teamBalance) {
        this.teamBalance = teamBalance;
        return this;
    }

    public int teamBalance() {
        return this.teamBalance;
    }

    public ServerboundRoomConfigPacket useBuildGun(final int useBuildGun) {
        this.useBuildGun = useBuildGun;
        return this;
    }

    public int useBuildGun() {
        return this.useBuildGun;
    }

    public ServerboundRoomConfigPacket itemPickup(final int itemPickup) {
        this.itemPickup = itemPickup;
        return this;
    }

    public int itemPickup() {
        return this.itemPickup;
    }

    public ServerboundRoomConfigPacket whereAlias(final String whereAlias) {
        this.whereAlias = whereAlias;
        return this;
    }

    public String whereAlias() {
        return this.whereAlias;
    }

    public ServerboundRoomConfigPacket pswd(final String pswd) {
        this.pswd = pswd;
        return this;
    }

    public String pswd() {
        return this.pswd;
    }

    public ServerboundRoomConfigPacket type(final int type) {
        this.type = type;
        return this;
    }

    public int type() {
        return this.type;
    }

    @Override
    public int packetId() {
        return 91;
    }

    @Override
    public void read(final ByteBuf buffer) {
        this.killCount = buffer.readIntLE();
        this.timeLimit = buffer.readIntLE();
        this.weaponOption = buffer.readIntLE();
        this.nWhere = buffer.readIntLE();
        this.breakInto = buffer.readIntLE();
        this.teamBalance = buffer.readIntLE();
        this.useBuildGun = buffer.readIntLE();
        this.itemPickup = buffer.readIntLE();
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.whereAlias = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.whereAlias = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        {
            final int length = buffer.readIntLE();
            if (length == 0) {
                this.pswd = "";
            } else {
                final byte[] bytes = new byte[length];
                buffer.readBytes(bytes);
                this.pswd = new String(bytes, StandardCharsets.UTF_16LE);
            }
        }
        this.type = buffer.readIntLE();
    }
}