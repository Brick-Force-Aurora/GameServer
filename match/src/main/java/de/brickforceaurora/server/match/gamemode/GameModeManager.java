package de.brickforceaurora.server.match.gamemode;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.net.INetListener;
import de.brickforceaurora.server.net.NetHandlerContainer;
import de.brickforceaurora.server.net.protocol.data.RoomType;
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
            this.container = new NetHandlerContainer(this, null);
        }

        @Override
        public NetHandlerContainer newContainer() {
            return container;
        }

    }

}
