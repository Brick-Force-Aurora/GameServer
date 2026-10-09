package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.PacketBuf;

import java.util.ArrayList;
import java.util.List;

public final class ClientboundEnterPacket implements IClientboundPacket {

	private int id;
	private String nickname;
	private String localIp;
	private int localPort;
	private String remoteIp;
	private int remotePort;
	private List<String> equipment = new ArrayList<>();
	private int status;
	private int xp;
	private int clanId;
	private String clanName;
	private int clanMark;
	private int rank;
	private int playerFlag; // byte (0-255)
	private List<String> weaponChanges = new ArrayList<>();
	private List<String> dropItems = new ArrayList<>();

	public int id() {
		return this.id;
	}

	public ClientboundEnterPacket id(int id) {
		this.id = id;
		return this;
	}

	public String nickname() {
		return this.nickname;
	}

	public ClientboundEnterPacket nickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public String localIp() {
		return this.localIp;
	}

	public ClientboundEnterPacket localIp(String localIp) {
		this.localIp = localIp;
		return this;
	}

	public int localPort() {
		return this.localPort;
	}

	public ClientboundEnterPacket localPort(int localPort) {
		this.localPort = localPort;
		return this;
	}

	public String remoteIp() {
		return this.remoteIp;
	}

	public ClientboundEnterPacket remoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
		return this;
	}

	public int remotePort() {
		return this.remotePort;
	}

	public ClientboundEnterPacket remotePort(int remotePort) {
		this.remotePort = remotePort;
		return this;
	}

	public List<String> equipment() {
		return this.equipment;
	}

	public ClientboundEnterPacket equipment(List<String> equipment) {
		this.equipment = equipment != null ? equipment : new ArrayList<>();
		return this;
	}

	public int status() {
		return this.status;
	}

	public ClientboundEnterPacket status(int status) {
		this.status = status;
		return this;
	}

	public int xp() {
		return this.xp;
	}

	public ClientboundEnterPacket xp(int xp) {
		this.xp = xp;
		return this;
	}

	public int clanId() {
		return this.clanId;
	}

	public ClientboundEnterPacket clanId(int clanId) {
		this.clanId = clanId;
		return this;
	}

	public String clanName() {
		return this.clanName;
	}

	public ClientboundEnterPacket clanName(String clanName) {
		this.clanName = clanName;
		return this;
	}

	public int clanMark() {
		return this.clanMark;
	}

	public ClientboundEnterPacket clanMark(int clanMark) {
		this.clanMark = clanMark;
		return this;
	}

	public int rank() {
		return this.rank;
	}

	public ClientboundEnterPacket rank(int rank) {
		this.rank = rank;
		return this;
	}

	public int playerFlag() {
		return this.playerFlag;
	}

	public ClientboundEnterPacket playerFlag(int playerFlag) {
		if (playerFlag < 0 || playerFlag > 255) {
			throw new IllegalArgumentException(
					"Value " + playerFlag + " is out of bounds of allowed number range of 0 - 255");
		}
		this.playerFlag = playerFlag;
		return this;
	}

	public List<String> weaponChanges() {
		return this.weaponChanges;
	}

	public ClientboundEnterPacket weaponChanges(List<String> weaponChanges) {
		this.weaponChanges = weaponChanges != null ? weaponChanges : new ArrayList<>();
		return this;
	}

	public List<String> dropItems() {
		return this.dropItems;
	}

	public ClientboundEnterPacket dropItems(List<String> dropItems) {
		this.dropItems = dropItems != null ? dropItems : new ArrayList<>();
		return this;
	}

	@Override
	public int packetId() {
		return 10;
	}

	@Override
	public void write(PacketBuf buf) {
		buf.writeInt(this.id);
		buf.writeString(this.nickname);
		buf.writeString(this.localIp);
		buf.writeInt(this.localPort);
		buf.writeString(this.remoteIp);
		buf.writeInt(this.remotePort);

		buf.writeInt(this.equipment.size());
		for (String item : this.equipment) {
			buf.writeString(item);
		}

		buf.writeInt(this.status);
		buf.writeInt(this.xp);
		buf.writeInt(this.clanId);
		buf.writeString(this.clanName);
		buf.writeInt(this.clanMark);
		buf.writeInt(this.rank);
		buf.writeByte(this.playerFlag);

		buf.writeInt(this.weaponChanges.size());
		for (String weapon : this.weaponChanges) {
			buf.writeString(weapon);
		}

		buf.writeInt(this.dropItems.size());
		for (String item : this.dropItems) {
			buf.writeString(item);
		}
	}
}