package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;

public final class ClientboundPlayerInfoPacket implements IClientboundPacket {

    private String nickname;
    private int xp;
    private int forcePoints;
    private int brickPoints;
    private int tokens;
    private int coins;
    private int starDust;
    private int apsType;
    private int apsLevel;
    private int gm;
    private int clanSeq;
    private String clanName;
    private int clanMark;
    private int clanLv;
    private int rank;
    private int heavy;
    private int assault;
    private int sniper;
    private int subMachine;
    private int handGun;
    private int melee;
    private int special;

    public ClientboundPlayerInfoPacket nickname(final String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String nickname() {
        return this.nickname;
    }

    public ClientboundPlayerInfoPacket xp(final int xp) {
        this.xp = xp;
        return this;
    }

    public int xp() {
        return this.xp;
    }

    public ClientboundPlayerInfoPacket forcePoints(final int forcePoints) {
        this.forcePoints = forcePoints;
        return this;
    }

    public int forcePoints() {
        return this.forcePoints;
    }

    public ClientboundPlayerInfoPacket brickPoints(final int brickPoints) {
        this.brickPoints = brickPoints;
        return this;
    }

    public int brickPoints() {
        return this.brickPoints;
    }

    public ClientboundPlayerInfoPacket tokens(final int tokens) {
        this.tokens = tokens;
        return this;
    }

    public int tokens() {
        return this.tokens;
    }

    public ClientboundPlayerInfoPacket coins(final int coins) {
        this.coins = coins;
        return this;
    }

    public int coins() {
        return this.coins;
    }

    public ClientboundPlayerInfoPacket starDust(final int starDust) {
        this.starDust = starDust;
        return this;
    }

    public int starDust() {
        return this.starDust;
    }

    public ClientboundPlayerInfoPacket apsType(final int apsType) {
        this.apsType = apsType;
        return this;
    }

    public int apsType() {
        return this.apsType;
    }

    public ClientboundPlayerInfoPacket apsLevel(final int apsLevel) {
        this.apsLevel = apsLevel;
        return this;
    }

    public int apsLevel() {
        return this.apsLevel;
    }

    public ClientboundPlayerInfoPacket gm(final int gm) {
        this.gm = gm;
        return this;
    }

    public int gm() {
        return this.gm;
    }

    public ClientboundPlayerInfoPacket clanSeq(final int clanSeq) {
        this.clanSeq = clanSeq;
        return this;
    }

    public int clanSeq() {
        return this.clanSeq;
    }

    public ClientboundPlayerInfoPacket clanName(final String clanName) {
        this.clanName = clanName;
        return this;
    }

    public String clanName() {
        return this.clanName;
    }

    public ClientboundPlayerInfoPacket clanMark(final int clanMark) {
        this.clanMark = clanMark;
        return this;
    }

    public int clanMark() {
        return this.clanMark;
    }

    public ClientboundPlayerInfoPacket clanLv(final int clanLv) {
        this.clanLv = clanLv;
        return this;
    }

    public int clanLv() {
        return this.clanLv;
    }

    public ClientboundPlayerInfoPacket rank(final int rank) {
        this.rank = rank;
        return this;
    }

    public int rank() {
        return this.rank;
    }

    public ClientboundPlayerInfoPacket heavy(final int heavy) {
        this.heavy = heavy;
        return this;
    }

    public int heavy() {
        return this.heavy;
    }

    public ClientboundPlayerInfoPacket assault(final int assault) {
        this.assault = assault;
        return this;
    }

    public int assault() {
        return this.assault;
    }

    public ClientboundPlayerInfoPacket sniper(final int sniper) {
        this.sniper = sniper;
        return this;
    }

    public int sniper() {
        return this.sniper;
    }

    public ClientboundPlayerInfoPacket subMachine(final int subMachine) {
        this.subMachine = subMachine;
        return this;
    }

    public int subMachine() {
        return this.subMachine;
    }

    public ClientboundPlayerInfoPacket handGun(final int handGun) {
        this.handGun = handGun;
        return this;
    }

    public int handGun() {
        return this.handGun;
    }

    public ClientboundPlayerInfoPacket melee(final int melee) {
        this.melee = melee;
        return this;
    }

    public int melee() {
        return this.melee;
    }

    public ClientboundPlayerInfoPacket special(final int special) {
        this.special = special;
        return this;
    }

    public int special() {
        return this.special;
    }

    @Override
    public int packetId() {
        return 27;
    }

    @Override
    public void write(final PacketBuf buf) {
        buf.writeString(this.nickname);
        buf.writeInt(this.xp);
        buf.writeInt(this.forcePoints);
        buf.writeInt(this.brickPoints);
        buf.writeInt(this.tokens);
        buf.writeInt(0); //Needs random unused value here?
        buf.writeInt(this.coins);
        buf.writeInt(this.starDust);
        buf.writeInt(this.apsType);
        buf.writeInt(this.apsLevel);
        buf.writeInt(this.gm);
        buf.writeInt(this.clanSeq);
        buf.writeString(this.clanName);
        buf.writeInt(this.clanMark);
        buf.writeInt(this.clanLv);
        buf.writeInt(this.rank);
        buf.writeInt(this.heavy);
        buf.writeInt(this.assault);
        buf.writeInt(this.sniper);
        buf.writeInt(this.subMachine);
        buf.writeInt(this.handGun);
        buf.writeInt(this.melee);
        buf.writeInt(this.special);
    }
}