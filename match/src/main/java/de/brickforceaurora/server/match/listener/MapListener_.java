package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundAllMapPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMyDownloadMapPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundMyRegisterMapPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundUserMapPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundAllMapPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundMyDownloadMapPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundMyRegisterMapPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundUserMapPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class MapListener_ implements INetListener {

    @PacketHandler
    public void onUserMap(final NetContext<ServerboundUserMapPacket> context) {
        int page = context.packet().page();
        context.client().send(new ClientboundUserMapPacket().page(0).maps(null));
    }

    @PacketHandler
    public void onMyRegisterMap(final NetContext<ServerboundMyRegisterMapPacket> context) {
        int prevPage = context.packet().prevPage();
        int nextPage = context.packet().nextPage();
        int indexer = context.packet().indexer();
        int modeMask = context.packet().modeMask();
        context.client().send(new ClientboundMyRegisterMapPacket().page(0).maps(null));
    }

    @PacketHandler
    public void onMyDownloadMap(final NetContext<ServerboundMyDownloadMapPacket> context) {
        int prevPage = context.packet().prevPage();
        int nextPage = context.packet().nextPage();
        int indexer = context.packet().indexer();
        int modeMask = context.packet().modeMask();
        context.client().send(new ClientboundMyDownloadMapPacket().page(0).maps(null));
    }

    @PacketHandler
    public void onAllMap(final NetContext<ServerboundAllMapPacket> context) {
        int prevPage = context.packet().prevPage();
        int nextPage = context.packet().nextPage();
        int indexer = context.packet().indexer();
        int modeMask = context.packet().modeMask();
        int flag = context.packet().flag();
        String filter = context.packet().filter();
        context.client().send(new ClientboundAllMapPacket().page(0).maps(null));
    }
}
