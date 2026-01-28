package de.brickforceaurora.gameserver.legacy.handler;

import de.brickforceaurora.gameserver.legacy.core.GameServerLogic;
import de.brickforceaurora.gameserver.legacy.protocol.MsgReference;

@FunctionalInterface
public interface MessageHandler {
    void handle(GameServerLogic server, MsgReference msg);
}
