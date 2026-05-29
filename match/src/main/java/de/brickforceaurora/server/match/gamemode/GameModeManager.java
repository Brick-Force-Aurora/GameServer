package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.NetHandler;
import de.brickforceaurora.server.net.NetHandlerContainer;
import de.brickforceaurora.server.net.protocol.data.RoomType;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.util.Enum2ObjectMap;

public class GameModeManager {

    private final Enum2ObjectMap<RoomType, GameMode<?>> modes = new Enum2ObjectMap<>(RoomType.class);
    private final GameNetListener netListener;

    public GameModeManager(final SnowFrame<MatchServerApp> frame) {
        frame.extension(GameMode.class, true).callInstances(mode -> {
            RoomType type = mode.roomType();
            if (type == null || modes.containsKey(type)) {
                frame.logger().error("Invalid or duplicate room type '{0}' for game mode '{1}'", type, mode.getClass().getName());
                return;
            }
            modes.put(type, mode);
        });
        netListener = new GameNetListener(modes.values());
    }

    public GameMode<?> modeByType(RoomType type) {
        return modes.get(type);
    }

    final INetListener netListener() {
        return netListener;
    }

    private static final class GameNetListener implements INetListener {

        private final NetHandlerContainer container;

        public GameNetListener(ObjectCollection<GameMode<?>> modes) {
            ObjectArrayList<NetHandler<?>> handlers = new ObjectArrayList<>();
            IntArrayList packetIds = new IntArrayList();
            for (GameMode<?> mode : modes) {
                for (GameNetHandlerContainer<?> container : mode.netHandlers()) {
                    container.handlers().forEach(handler -> {
                        if (packetIds.contains(handler.packetId())) {
                            return;
                        }
                        packetIds.add(handler.packetId());
                        handlers.add(new NetHandler<>(handler.packetId(), this::handlePacket));
                    });
                }
            }
            this.container = new NetHandlerContainer(this, handlers.toArray(NetHandler[]::new));
        }

        @Override
        public NetHandlerContainer newContainer() {
            return container;
        }

        private void handlePacket(NetContext<?> context) {
            Room room = context.client().attr(Room.ATTR_ROOM, Room.class);
            if (room == null) {
                // TODO: WHAT???
                return;
            }
            room.mode().handlePacket(room, context);
        }

    }

}
