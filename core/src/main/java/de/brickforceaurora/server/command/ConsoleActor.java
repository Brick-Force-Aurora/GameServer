package de.brickforceaurora.server.command;

import java.util.UUID;

import de.brickforceaurora.server.ServerApp;
import me.lauriichan.laylib.command.Actor;
import me.lauriichan.laylib.localization.MessageManager;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.util.logger.FileLogger;

public final class ConsoleActor extends Actor<SnowFrame<? extends ServerApp<?>>> {

    private static final String COMMAND_LOG_FORMAT = "[%s][COMMAND/%s] %s";
    
    private final FileLogger logger;

    public ConsoleActor(SnowFrame<? extends ServerApp<?>> handle, MessageManager messageManager) {
        super(handle, messageManager);
        this.logger = (FileLogger) handle.logger();
    }

    @Override
    public UUID getId() {
        return Actor.IMPL_ID;
    }

    @Override
    public String getName() {
        return "Console";
    }
    
    @Override
    public void sendMessage(String message) {
        logger.print(COMMAND_LOG_FORMAT, message);
    }
    
    @Override
    public boolean hasPermission(String permission) {
        // Console actor is permitted to do everything :)
        return true;
    }
    
}
