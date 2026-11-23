package de.brickforceaurora.server.match.game;

import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.NetManager;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomType;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundRoomConfigPacket;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectLists;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.ExtensionPoint;
import me.lauriichan.snowframe.extension.IExtension;

@ExtensionPoint
public abstract class GameMode<D extends GameData> implements IExtension {

    private final Class<D> dataType;
    private final RoomType roomType;

    private final ObjectList<GameNetHandlerContainer<D>> netHandlers;

    @SuppressWarnings({
        "rawtypes",
        "unchecked"
    })
    public GameMode(final SnowFrame<?> snowFrame, final Class<D> dataType, final RoomType roomType) {
        this.dataType = dataType;
        this.roomType = roomType;

        final ObjectArrayList<GameNetHandlerContainer<D>> netHandlers = new ObjectArrayList<>();
        final Class<?> gameModeClass = getClass();
        for (final Class<? extends IGameNetListener> listenerClass : snowFrame.extension(IGameNetListener.class, false)
            .extensionClasses()) {
            final GameTarget target = listenerClass.getDeclaredAnnotation(GameTarget.class);
            if (target == null || target.value() == null || !target.value().isAssignableFrom(gameModeClass)
                || !target.data().isAssignableFrom(dataType)) {
                continue;
            }
            IGameNetListener listener;
            try {
                listener = snowFrame.invoker().invoke(listenerClass, this);
            } catch (final Throwable e) {
                snowFrame.logger().error("Failed to create game mode listener '{0}'", e, listenerClass.getName());
                continue;
            }
            netHandlers.add(listener.newContainer());
        }
        this.netHandlers = ObjectLists.unmodifiable(netHandlers);
    }

    public final Class<D> dataType() {
        return dataType;
    }

    public final RoomType roomType() {
        return roomType;
    }

    final ObjectList<GameNetHandlerContainer<D>> netHandlers() {
        return netHandlers;
    }

    public final void handlePacket(final Room room, final NetContext<?> context) {
        final GameData rawData = room.gameData();
        if (!dataType.isInstance(rawData)) {
            // HUH WHAT
            return;
        }
        final D data = dataType.cast(rawData);
        for (final GameNetHandlerContainer<D> netHandler : netHandlers) {
            netHandler.handlePacket(room, data, context);
        }
    }

    public abstract GameData createGameDataFor(final Room room);

    public abstract void handleRoomCreation(final Room room, final NetContext<ServerboundCreateRoomPacket> context);

    public abstract void handleRoomJoin(final NetManager<?> netManager, final Room room, final BFClient client);

    public abstract void handleRoomUpdate(final Room room, final NetContext<ServerboundRoomConfigPacket> context);

    public abstract void handleRoomLeave(final Room room, final BFClient client);

    public void handleMasterChange(final Room room) {}

}
