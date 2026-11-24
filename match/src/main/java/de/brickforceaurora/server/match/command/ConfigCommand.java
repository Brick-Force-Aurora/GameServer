package de.brickforceaurora.server.match.command;

import de.brickforceaurora.server.command.BFActor;
import de.brickforceaurora.server.command.ICommandExtension;
import de.brickforceaurora.server.match.MatchServerApp;
import me.lauriichan.laylib.command.annotation.Action;
import me.lauriichan.laylib.command.annotation.Command;
import me.lauriichan.laylib.command.annotation.Description;
import me.lauriichan.laylib.command.annotation.Permission;
import me.lauriichan.snowframe.ConfigModule;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@Permission("server.config")
@Command(name = "config", description = "Config management")
public class ConfigCommand implements ICommandExtension {

    @Action("reload")
    @Description("Reloads all configs")
    public void configReload(BFActor<MatchServerApp, ?> actor) {
        actor.snowFrame().module(ConfigModule.class).manager().reload();
        actor.sendMessage("Reloaded configs");
    }

    @Action("save")
    @Description("Saves all configs")
    public void configSave(BFActor<MatchServerApp, ?> actor) {
        actor.snowFrame().module(ConfigModule.class).manager().save();
        actor.sendMessage("Saved configs");
    }

}
