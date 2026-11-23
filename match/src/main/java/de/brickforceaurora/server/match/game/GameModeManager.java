package de.brickforceaurora.server.match.game;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.net.tcp.INetListener;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.NetHandler;
import de.brickforceaurora.server.net.tcp.NetHandlerContainer;
import de.brickforceaurora.server.net.tcp.protocol.data.RoomType;
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
            final RoomType type = mode.roomType();
            if (type == null || modes.containsKey(type)) {
                frame.logger().error("Invalid or duplicate room type '{0}' for game mode '{1}'", type, mode.getClass().getName());
                return;
            }
            modes.put(type, mode);
        });
        netListener = new GameNetListener(modes.values());
    }

    public GameMode<?> modeByType(final RoomType type) {
        return modes.get(type);
    }

    final INetListener netListener() {
        return netListener;
    }

    private static final class GameNetListener implements INetListener {

        private final NetHandlerContainer container;

        public GameNetListener(final ObjectCollection<GameMode<?>> modes) {
            final ObjectArrayList<NetHandler<?>> handlers = new ObjectArrayList<>();
            final IntArrayList packetIds = new IntArrayList();
            for (final GameMode<?> mode : modes) {
                for (final GameNetHandlerContainer<?> container : mode.netHandlers()) {
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

        private void handlePacket(final NetContext<?> context) {
            final Room room = context.client().attr(Room.ATTR_ROOM, Room.class);
            if (room == null) {
                // TODO: WHAT???
                return;
            }
            room.mode().handlePacket(room, context);
        }

    }

}
