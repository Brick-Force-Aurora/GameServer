package de.brickforceaurora.server.command;

import java.util.UUID;

import de.brickforceaurora.server.ServerApp;
import me.lauriichan.laylib.command.Actor;
import me.lauriichan.laylib.localization.MessageManager;
import me.lauriichan.snowframe.SnowFrame;

public class ConsoleActor extends Actor<SnowFrame<? extends ServerApp<?>>> {

    public ConsoleActor(SnowFrame<? extends ServerApp<?>> handle, MessageManager messageManager) {
        super(handle, messageManager);
    }

    @Override
    public UUID getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
