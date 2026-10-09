package de.brickforceaurora.server.net.protocol.clientbound.original;

import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.data.ItemUsage;
import de.brickforceaurora.server.net.protocol.PacketBuf;

import java.util.ArrayList;
import java.util.List;

public final class ClientboundItemListPacket implements IClientboundPacket {

	public record ItemEntry(
			long seq,
			String code,
			ItemUsage usage,
			int remain,
			byte premium,
			int durability
	) {}

	private List<ItemEntry> items = new ArrayList<>();

	public ClientboundItemListPacket items(List<ItemEntry> items) {
		this.items = items;
		return this;
	}

	public List<ItemEntry> items() {
		return this.items;
	}

	public ClientboundItemListPacket addItem(ItemEntry item) {
		this.items.add(item);
		return this;
	}

	@Override
	public int packetId() {
		return 464;
	}

	@Override
	public final void write(PacketBuf buf) {
		buf.writeInt(this.items != null ? this.items.size() : 0);

		if (this.items != null) {
			for (ItemEntry item : this.items) {
				buf.writeLong(item.seq());
				buf.writeString(item.code());
				buf.writeByte(item.usage() != null ? item.usage().id() : ItemUsage.NOT_USING.id());
				buf.writeInt(item.remain());
				buf.writeByte(item.premium());
				buf.writeInt(item.durability());
			}
		}
	}
}