package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

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

	public final ClientboundPlayerInfoPacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public final String nickname() {
		return this.nickname;
	}

	public final ClientboundPlayerInfoPacket xp(int xp) {
		this.xp = xp;
		return this;
	}

	public final int xp() {
		return this.xp;
	}

	public final ClientboundPlayerInfoPacket forcePoints(int val3) {
		this.forcePoints = forcePoints;
		return this;
	}

	public final int forcePoints() {
		return this.forcePoints;
	}

	public final ClientboundPlayerInfoPacket brickPoints(int brickPoints) {
		this.brickPoints = brickPoints;
		return this;
	}

	public final int brickPoints() {
		return this.brickPoints;
	}

	public final ClientboundPlayerInfoPacket tokens(int tokens) {
		this.tokens = tokens;
		return this;
	}

	public final int tokens() {
		return this.tokens;
	}

	public final ClientboundPlayerInfoPacket coins(int coins) {
		this.coins = coins;
		return this;
	}

	public final int coins() {
		return this.coins;
	}

	public final ClientboundPlayerInfoPacket starDust(int starDust) {
		this.starDust = starDust;
		return this;
	}

	public final int starDust() {
		return this.starDust;
	}

	public final ClientboundPlayerInfoPacket apsType(int apsType) {
		this.apsType = apsType;
		return this;
	}

	public final int apsType() {
		return this.apsType;
	}

	public final ClientboundPlayerInfoPacket apsLevel(int apsLevel) {
		this.apsLevel = apsLevel;
		return this;
	}

	public final int apsLevel() {
		return this.apsLevel;
	}

	public final ClientboundPlayerInfoPacket gm(int gm) {
		this.gm = gm;
		return this;
	}

	public final int gm() {
		return this.gm;
	}

	public final ClientboundPlayerInfoPacket clanSeq(int clanSeq) {
		this.clanSeq = clanSeq;
		return this;
	}

	public final int clanSeq() {
		return this.clanSeq;
	}

	public final ClientboundPlayerInfoPacket clanName(String clanName) {
		this.clanName = clanName;
		return this;
	}

	public final String clanName() {
		return this.clanName;
	}

	public final ClientboundPlayerInfoPacket clanMark(int clanMark) {
		this.clanMark = clanMark;
		return this;
	}

	public final int clanMark() {
		return this.clanMark;
	}

	public final ClientboundPlayerInfoPacket clanLv(int clanLv) {
		this.clanLv = clanLv;
		return this;
	}

	public final int clanLv() {
		return this.clanLv;
	}

	public final ClientboundPlayerInfoPacket rank(int rank) {
		this.rank = rank;
		return this;
	}

	public final int rank() {
		return this.rank;
	}

	public final ClientboundPlayerInfoPacket heavy(int heavy) {
		this.heavy = heavy;
		return this;
	}

	public final int heavy() {
		return this.heavy;
	}

	public final ClientboundPlayerInfoPacket assault(int assault) {
		this.assault = assault;
		return this;
	}

	public final int assault() {
		return this.assault;
	}

	public final ClientboundPlayerInfoPacket sniper(int sniper) {
		this.sniper = sniper;
		return this;
	}

	public final int sniper() {
		return this.sniper;
	}

	public final ClientboundPlayerInfoPacket subMachine(int subMachine) {
		this.subMachine = subMachine;
		return this;
	}

	public final int subMachine() {
		return this.subMachine;
	}

	public final ClientboundPlayerInfoPacket handGun(int handGun) {
		this.handGun = handGun;
		return this;
	}

	public final int handGun() {
		return this.handGun;
	}

	public final ClientboundPlayerInfoPacket melee(int melee) {
		this.melee = melee;
		return this;
	}

	public final int melee() {
		return this.melee;
	}

	public final ClientboundPlayerInfoPacket special(int special) {
		this.special = special;
		return this;
	}

	public final int special() {
		return this.special;
	}

	@Override
	public int packetId() {
		return 27;
	}

	@Override
	public final void write(PacketBuf buf) {
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