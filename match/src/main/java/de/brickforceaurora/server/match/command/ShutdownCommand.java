package de.brickforceaurora.server.match.command;

import de.brickforceaurora.server.command.ICommandExtension;
import de.brickforceaurora.server.match.Main;
import me.lauriichan.laylib.command.Actor;
import me.lauriichan.laylib.command.annotation.Action;
import me.lauriichan.laylib.command.annotation.Command;
import me.lauriichan.laylib.command.annotation.Permission;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@Permission("server.shutdown")
@Command(name = "exit", aliases = {
    "shutdown",
    "stop",
    "quit"
}, description = "Shuts down the server")
public class ShutdownCommand implements ICommandExtension {

    @Action("")
    public void run(Actor<?> actor) {
        actor.sendMessage("Shutting down");
        Main.shutdown();
    }

}
