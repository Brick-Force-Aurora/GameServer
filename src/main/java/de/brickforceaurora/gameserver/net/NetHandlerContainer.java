package de.brickforceaurora.gameserver.net;

import de.brickforceaurora.gameserver.net.protocol.IPacket;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;

public final class NetHandlerContainer {

    private final INetListener listener;

    private final IntList handledPackets;
    private final ObjectList<NetHandler<?>> handlers;

    public NetHandlerContainer(final INetListener listener, final NetHandler<?>[] handlers) {
        final ObjectArrayList<NetHandler<?>> handlerList = new ObjectArrayList<>();
        final IntArrayList handledPackets = new IntArrayList();
        for (final NetHandler<?> handler : handlers) {
            handlerList.add(handler);
            if (!handledPackets.contains(handler.packetId())) {
                handledPackets.add(handler.packetId());
            }
        }
        this.listener = listener;
        this.handledPackets = IntLists.unmodifiable(handledPackets);
        this.handlers = ObjectLists.unmodifiable(handlerList);
    }

    public INetListener listener() {
        return listener;
    }

    public ObjectList<NetHandler<?>> handlers() {
        return handlers;
    }

    public boolean supports(final int packetId) {
        return handledPackets.contains(packetId);
    }

    void handlePacket(final NetContext<?> context) {
        for (final NetHandler<?> handler : handlers) {
            if (context.packet().packetId() != handler.packetId()) {
                continue;
            }
            handle(handler, context);
        }
    }

    @SuppressWarnings("unchecked")
    private <P extends IPacket> void handle(final NetHandler<P> handler, final NetContext<?> context) {
        handler.handle((NetContext<P>) context);
    }

}
