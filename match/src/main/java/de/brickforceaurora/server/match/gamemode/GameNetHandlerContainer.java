package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.protocol.IPacket;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;

public final class GameNetHandlerContainer<D extends GameData> {

    private final IGameNetListener<D> listener;

    private final IntList handledPackets;
    private final ObjectList<GameNetHandler<D, ?>> handlers;

    public GameNetHandlerContainer(final IGameNetListener<D> listener, final GameNetHandler<D, ?>[] handlers) {
        final ObjectArrayList<GameNetHandler<D, ?>> handlerList = new ObjectArrayList<>();
        final IntArrayList handledPackets = new IntArrayList();
        for (final GameNetHandler<D, ?> handler : handlers) {
            handlerList.add(handler);
            if (!handledPackets.contains(handler.packetId())) {
                handledPackets.add(handler.packetId());
            }
        }
        this.listener = listener;
        this.handledPackets = IntLists.unmodifiable(handledPackets);
        this.handlers = ObjectLists.unmodifiable(handlerList);
    }

    public IGameNetListener<D> listener() {
        return listener;
    }

    public ObjectList<GameNetHandler<D, ?>> handlers() {
        return handlers;
    }

    public boolean supports(final int packetId) {
        return handledPackets.contains(packetId);
    }

    void handlePacket(final Room room, final D gameData, final NetContext<?> context) {
        for (final GameNetHandler<D, ?> handler : handlers) {
            if (context.packet().packetId() != handler.packetId()) {
                continue;
            }
            handle(room, gameData, handler, context);
        }
    }

    @SuppressWarnings("unchecked")
    private <P extends IPacket> void handle(final Room room, final D gameData, final GameNetHandler<D, P> handler,
        final NetContext<?> context) {
        handler.handle(room, gameData, (NetContext<P>) context);
    }

}
