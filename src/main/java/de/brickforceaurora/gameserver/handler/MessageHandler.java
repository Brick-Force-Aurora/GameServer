package de.brickforceaurora.gameserver.handler;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.net.MsgReference;

@FunctionalInterface
public interface MessageHandler {
    void handle(GameServerLogic server, MsgReference msg);
}
