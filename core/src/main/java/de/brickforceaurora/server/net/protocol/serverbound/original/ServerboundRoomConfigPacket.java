package de.brickforceaurora.server.net.protocol.serverbound.original;

import de.brickforceaurora.server.net.protocol.IServerboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.RoomType;

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
    private RoomType type;

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

    public ServerboundRoomConfigPacket type(final RoomType type) {
        this.type = type;
        return this;
    }

    public RoomType type() {
        return this.type;
    }

    @Override
    public int packetId() {
        return 91;
    }

    @Override
    public void read(final PacketBuf buf) {
        this.killCount = buf.readInt();
        this.timeLimit = buf.readInt();
        this.weaponOption = buf.readInt();
        this.nWhere = buf.readInt();
        this.breakInto = buf.readInt();
        this.teamBalance = buf.readInt();
        this.useBuildGun = buf.readInt();
        this.itemPickup = buf.readInt();
        this.whereAlias = buf.readString();
        this.pswd = buf.readString();
        this.type = RoomType.byId(buf.readInt());
    }
}