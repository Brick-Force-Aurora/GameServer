package de.brickforceaurora.gameserver.legacy.handler;

import java.util.HashMap;
import java.util.Map;

import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;

public final class MessageDispatcher {

    private final Map<Integer, MessageHandler> handlers = new HashMap<>();

    public void register(final int msgId, final MessageHandler handler) {
        handlers.put(msgId, handler);
    }

    public void dispatch(final GameServerLogic server, final MsgReference msg) {
        final MessageHandler handler = handlers.get(msg.msg.id());
        if (handler != null) {
            handler.handle(server, msg);
        } else {
            server.logger().warning("No handler for message ID {0}", msg.msg.id());
        }
    }
}
