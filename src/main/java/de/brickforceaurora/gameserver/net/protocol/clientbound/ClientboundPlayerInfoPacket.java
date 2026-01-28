package de.brickforceaurora.gameserver.net.protocol.clientbound;

import java.nio.charset.StandardCharsets;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;

public final class ClientboundPlayerInfoPacket implements IClientboundPacket {

    private String name;
    private int playerXp;
    private int forcePoints;
    private int brickPoints;
    private int tokens;
    private int unused; 		//unused, send empty int
    private int coins;
    private int starDust;
    private int apsType; 		//Age Protection Type (China, Korea)
    private int apsLevel; 		//Age Protection Level (China, Korea)
    private int gm; 			//has to do something with anitcheat
    private int clanId;
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

    public ClientboundPlayerInfoPacket name(final String name) {
        this.name = name;
        return this;
    }

    public String name() {
        return this.name;
    }

    public ClientboundPlayerInfoPacket playerXp(final int playerXp) {
        this.playerXp = playerXp;
        return this;
    }

    public int playerXp() {
        return this.playerXp;
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

    public ClientboundPlayerInfoPacket unused(final int unused) {
        this.unused = unused;
        return this;
    }

    public int unused() {
        return this.unused;
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

    public ClientboundPlayerInfoPacket clanId(final int clanId) {
        this.clanId = clanId;
        return this;
    }

    public int clanId() {
        return this.clanId;
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
    public void write(final ByteBuf buffer) {
        if (this.name.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.name.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.playerXp);
        buffer.writeIntLE(this.forcePoints);
        buffer.writeIntLE(this.brickPoints);
        buffer.writeIntLE(this.tokens);
        buffer.writeIntLE(this.unused);
        buffer.writeIntLE(this.coins);
        buffer.writeIntLE(this.starDust);
        buffer.writeIntLE(this.apsType);
        buffer.writeIntLE(this.apsLevel);
        buffer.writeIntLE(this.gm);
        buffer.writeIntLE(this.clanId);
        if (this.clanName.isEmpty()) {
            buffer.writeIntLE(0);
        } else {
            final byte[] bytes = this.clanName.getBytes(StandardCharsets.UTF_16LE);
            buffer.writeIntLE(bytes.length);
            buffer.writeBytes(bytes);
        }
        buffer.writeIntLE(this.clanMark);
        buffer.writeIntLE(this.clanLv);
        buffer.writeIntLE(this.rank);
        buffer.writeIntLE(this.heavy);
        buffer.writeIntLE(this.assault);
        buffer.writeIntLE(this.sniper);
        buffer.writeIntLE(this.subMachine);
        buffer.writeIntLE(this.handGun);
        buffer.writeIntLE(this.melee);
        buffer.writeIntLE(this.special);
    }
}