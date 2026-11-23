package de.brickforceaurora.server.net.tcp.protocol.clientbound.original;

import de.brickforceaurora.server.net.tcp.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.tcp.protocol.PacketBuf;
import de.brickforceaurora.server.net.tcp.protocol.data.DroppedItem;

public final class ClientboundDropItemPacket implements IClientboundPacket {

    private DroppedItem item;

    public ClientboundDropItemPacket item(final DroppedItem item) {
        this.item = item;
        return this;
    }

    public DroppedItem item() {
        return this.item;
    }

    @Override
    public int packetId() {
        return 527;
    }

    @Override
    public void write(final PacketBuf buf) {
        item.toBuffer(buf);
    }
}
