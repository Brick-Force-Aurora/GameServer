package de.brickforceaurora.server.match.command;

import de.brickforceaurora.server.command.BFActor;
import de.brickforceaurora.server.command.ICommandExtension;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.command.message.Messages;
import de.brickforceaurora.server.match.user.ClientActor;
import de.brickforceaurora.server.net.tcp.BFClient;
import me.lauriichan.laylib.command.annotation.Action;
import me.lauriichan.laylib.command.annotation.Argument;
import me.lauriichan.laylib.command.annotation.Command;
import me.lauriichan.laylib.command.annotation.Permission;
import me.lauriichan.laylib.localization.Key;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@Permission("server.control")
@Command(name = "control", description = "Controls the server")
public class ControlCommand implements ICommandExtension {

    @Action("maintenance")
    public void maintenance(BFActor<MatchServerApp, ?> actor) {
        actor.sendTranslatedMessage(Messages.COMMAND_CONTROL_MAINTENANCE_STATE,
            Key.of("state", actor.app().server().isInMaintenance() ? "enabled" : "disabled"));
    }

    @Action("maintenance disable")
    @Permission("server.maintenance")
    public void maintenanceDisable(BFActor<MatchServerApp, ?> actor) {
        if (actor.app().server().disableMaintenance()) {
            actor.sendTranslatedMessage(Messages.COMMAND_CONTROL_MAINTENANCE_DISABLE_SUCCESS);
            return;
        }
        actor.sendTranslatedMessage(Messages.COMMAND_CONTROL_MAINTENANCE_DISABLE_FAILURE);
    }

    @Action("maintenance enable")
    @Permission("server.maintenance")
    public void maintenanceEnable(BFActor<MatchServerApp, ?> actor, @Argument(optional = true) String maintenanceText) {
        if (actor.app().server().enableMaintenance(maintenanceText)) {
            maintenanceText = actor.app().server().maintenanceText();
            actor.sendTranslatedMessage(Messages.COMMAND_CONTROL_MAINTENANCE_ENABLE_SUCCESS, Key.of("text", maintenanceText));
            BFClient[] clients = actor.app().server().netManager().activeClients().toArray(BFClient[]::new);
            for (BFClient client : clients) {
                ClientActor clientActor = ClientActor.of(client);
                if (clientActor == null || !clientActor.hasPermission("server.maintenance")) {
                    client.disconnect(maintenanceText);
                }
            }
            return;
        }
        actor.sendTranslatedMessage(Messages.COMMAND_CONTROL_MAINTENANCE_ENABLE_FAILURE);
    }

}
