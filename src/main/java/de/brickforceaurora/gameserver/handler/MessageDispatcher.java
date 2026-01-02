package de.brickforceaurora.gameserver.handler;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.net.MsgReference;

import java.util.HashMap;
import java.util.Map;

public final class MessageDispatcher {

    private final Map<Integer, MessageHandler> handlers = new HashMap<>();

    public void register(int msgId, MessageHandler handler) {
        handlers.put(msgId, handler);
    }

    public void dispatch(GameServerLogic server, MsgReference msg) {
        MessageHandler handler = handlers.get(msg.msg.id());
        if (handler != null) {
            handler.handle(server, msg);
        } else {
            server.logger().warning("No handler for message ID {0}", msg.msg.id());
        }
    }
}

