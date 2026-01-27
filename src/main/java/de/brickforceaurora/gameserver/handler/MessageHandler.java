package de.brickforceaurora.gameserver.handler;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.protocol.MsgReference;

@FunctionalInterface
public interface MessageHandler {
    void handle(GameServerLogic server, MsgReference msg);
}
