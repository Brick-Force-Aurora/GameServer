package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundCreateRoomPacket;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundRoomConfigPacket;
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
    public GameMode(SnowFrame<?> snowFrame, final Class<D> dataType, final RoomType roomType) {
        this.dataType = dataType;
        this.roomType = roomType;

        ObjectArrayList<GameNetHandlerContainer<D>> netHandlers = new ObjectArrayList<>();
        Class<?> gameModeClass = getClass();
        for (Class<? extends IGameNetListener> listenerClass : snowFrame.extension(IGameNetListener.class, false).extensionClasses()) {
            GameTarget target = listenerClass.getDeclaredAnnotation(GameTarget.class);
            if (target == null || target.value() == null || !gameModeClass.isAssignableFrom(target.value())) {
                continue;
            }
            IGameNetListener listener;
            try {
                listener = snowFrame.invoker().invoke(listenerClass, this);
            } catch (Throwable e) {
                snowFrame.logger().error("Failed to create game mode listener '{0}'", e, listenerClass.getName());
                continue;
            }
            netHandlers.add((GameNetHandlerContainer<D>) listener.newContainer());
        }
        this.netHandlers = ObjectLists.unmodifiable(netHandlers);
    }

    public final Class<D> dataType() {
        return dataType;
    }

    public final RoomType roomType() {
        return roomType;
    }

    public final void handlePacket(Room room, NetContext<?> context) {
        GameData rawData = room.gameData();
        if (!dataType.isInstance(rawData)) {
            // HUH WHAT
            return;
        }
        D data = dataType.cast(rawData);
        for (GameNetHandlerContainer<D> netHandler : netHandlers) {
            netHandler.handlePacket(room, data, context);
        }
    }
    
    public abstract GameData createGameDataFor(final Room room);
    
    public abstract void handleRoomCreation(final Room room, final NetContext<ServerboundCreateRoomPacket> context);
    
    public abstract void handleRoomUpdate(final Room room, final NetContext<ServerboundRoomConfigPacket> context);
    
}
