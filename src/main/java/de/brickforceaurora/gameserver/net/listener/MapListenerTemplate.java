package de.brickforceaurora.gameserver.net.listener;

import java.util.List;

import de.brickforceaurora.gameserver.legacy.maps.RegMap;
import de.brickforceaurora.gameserver.legacy.maps.RegMapManager;
import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.INetListener;
import de.brickforceaurora.gameserver.net.NetContext;
import de.brickforceaurora.gameserver.net.PacketHandler;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundMyDownloadMapPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundMyRegisterMapPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundUserMapPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundAllMapPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundMyDownloadMapPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundMyRegisterMapPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundUserMapPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class MapListenerTemplate implements INetListener {

    @PacketHandler
    public void onMyRegisteredMap(final NetContext<ServerboundMyRegisterMapPacket> context) {
        //return registeredMaps for player
        final BFClient client = context.client();

        final int mapsPerPage = 12;
        final int offset = context.packet().nextPage() * mapsPerPage;
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        final int remaining = maps.size() - offset;
        final int count = Math.min(remaining, mapsPerPage);

        client.send(new ClientboundMyRegisterMapPacket().page(context.packet().nextPage()).count(count).offset(offset)
            .regMaps(maps.toArray(RegMap[]::new)));
    }

    @PacketHandler
    public void onMyDownloadedMap(final NetContext<ServerboundMyDownloadMapPacket> context) {
        final BFClient client = context.client();

        final int mapsPerPage = 12;
        final int offset = context.packet().nextPage() * mapsPerPage;
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        final int remaining = maps.size() - offset;
        final int count = Math.min(remaining, mapsPerPage);

        client.send(new ClientboundMyDownloadMapPacket().page(context.packet().nextPage()).count(count).offset(offset)
            .regMaps(maps.toArray(RegMap[]::new)));
    }

    @PacketHandler
    public void onAllMaps(final NetContext<ServerboundAllMapPacket> context) {
        final BFClient client = context.client();
        //return all maps
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        client.send(new ClientboundMyDownloadMapPacket().page(context.packet().nextPage()).regMaps(maps.toArray(RegMap[]::new)));
    }

    @PacketHandler
    public void onUserMap(final NetContext<ServerboundUserMapPacket> context) {
        final BFClient client = context.client();

        final int mapsPerPage = 12;
        final int offset = context.packet().page() * mapsPerPage;
        final List<RegMap> maps = RegMapManager.getInstance().getMapsAsList();
        final int remaining = maps.size() - offset;
        final int count = Math.min(remaining, mapsPerPage);
        client.send(
            new ClientboundUserMapPacket().page(context.packet().page()).count(count).offset(offset).regMaps(maps.toArray(RegMap[]::new)));
    }
}
