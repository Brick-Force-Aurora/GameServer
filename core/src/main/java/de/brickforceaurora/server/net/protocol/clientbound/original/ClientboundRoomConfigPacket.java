package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.net.protocol.data.RoomType;

public final class ClientboundRoomConfigPacket implements IClientboundPacket {

    private int mapId;
    private String mapAlias;
    private int weaponOption;
    private int timeLimit;
    private int killCount;
    private boolean canJoinMidGame;
    private boolean autoBalance;
    private boolean allowBuildGun;
    private String password;
    private int commented;
    private RoomType roomType;
    private boolean drop;
    private boolean wanted;

    public ClientboundRoomConfigPacket mapId(final int mapId) {
        this.mapId = mapId;
        return this;
    }

    public int mapId() {
        return this.mapId;
    }

    public ClientboundRoomConfigPacket mapAlias(final String mapAlias) {
        this.mapAlias = mapAlias;
        return this;
    }

    public String mapAlias() {
        return this.mapAlias;
    }

    public ClientboundRoomConfigPacket weaponOption(final int weaponOption) {
        this.weaponOption = weaponOption;
        return this;
    }

    public int weaponOption() {
        return this.weaponOption;
    }

    public ClientboundRoomConfigPacket timeLimit(final int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public int timeLimit() {
        return this.timeLimit;
    }

    public ClientboundRoomConfigPacket killCount(final int killCount) {
        this.killCount = killCount;
        return this;
    }

    public int killCount() {
        return this.killCount;
    }

    public ClientboundRoomConfigPacket canJoinMidGame(final boolean canJoinMidGame) {
        this.canJoinMidGame = canJoinMidGame;
        return this;
    }

    public boolean canJoinMidGame() {
        return this.canJoinMidGame;
    }

    public ClientboundRoomConfigPacket autoBalance(final boolean autoBalance) {
        this.autoBalance = autoBalance;
        return this;
    }

    public boolean autoBalance() {
        return this.autoBalance;
    }

    public ClientboundRoomConfigPacket allowBuildGun(final boolean allowBuildGun) {
        this.allowBuildGun = allowBuildGun;
        return this;
    }

    public boolean allowBuildGun() {
        return this.allowBuildGun;
    }

    public ClientboundRoomConfigPacket password(final String password) {
        this.password = password;
        return this;
    }

    public String password() {
        return this.password;
    }

    public ClientboundRoomConfigPacket commented(final int commented) {
        if (commented > 255L || commented < 0L) {
            throw new IllegalArgumentException("Value " + commented + " is out of bounds of allowed number range of 0 - 255");
        }
        this.commented = commented;
        return this;
    }

    public int commented() {
        return this.commented;
    }

    public ClientboundRoomConfigPacket roomType(final RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public RoomType roomType() {
        return this.roomType;
    }

    public ClientboundRoomConfigPacket drop(final boolean drop) {
        this.drop = drop;
        return this;
    }

    public boolean drop() {
        return this.drop;
    }

    public ClientboundRoomConfigPacket wanted(final boolean wanted) {
        this.wanted = wanted;
        return this;
    }

    public boolean wanted() {
        return this.wanted;
    }

    @Override
    public int packetId() {
        return 92;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeInt(this.mapId);
        buf.writeString(this.mapAlias);
        buf.writeInt(this.weaponOption);
        buf.writeInt(this.timeLimit);
        buf.writeInt(this.killCount);
        buf.writeBoolean(this.canJoinMidGame);
        buf.writeBoolean(this.autoBalance);
        buf.writeBoolean(this.allowBuildGun);
        buf.writeString(this.password);
        buf.writeByte(this.commented);
        buf.writeInt(this.roomType.id());
        buf.writeBoolean(this.drop);
        buf.writeBoolean(this.wanted);
    }
}