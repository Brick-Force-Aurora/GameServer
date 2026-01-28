package de.brickforceaurora.gameserver.net.protocol.clientbound;

import de.brickforceaurora.gameserver.net.protocol.IClientboundPacket;
import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;

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

	public final ClientboundPlayerInfoPacket name(String name) {
		this.name = name;
		return this;
	}

	public final String name() {
		return this.name;
	}

	public final ClientboundPlayerInfoPacket playerXp(int playerXp) {
		this.playerXp = playerXp;
		return this;
	}

	public final int playerXp() {
		return this.playerXp;
	}

	public final ClientboundPlayerInfoPacket forcePoints(int forcePoints) {
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

	public final ClientboundPlayerInfoPacket unused(int unused) {
		this.unused = unused;
		return this;
	}

	public final int unused() {
		return this.unused;
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

	public final ClientboundPlayerInfoPacket clanId(int clanId) {
		this.clanId = clanId;
		return this;
	}

	public final int clanId() {
		return this.clanId;
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
	public final void write(ByteBuf buffer) {
		if (this.name.isEmpty()) {
			buffer.writeIntLE(0);
		} else {
			byte[] bytes = this.name.getBytes(StandardCharsets.UTF_16LE);
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
			byte[] bytes = this.clanName.getBytes(StandardCharsets.UTF_16LE);
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