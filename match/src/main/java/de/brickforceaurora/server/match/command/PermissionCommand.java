package de.brickforceaurora.server.match.command;

import java.util.UUID;
import java.util.function.Consumer;

import de.brickforceaurora.server.command.BFActor;
import de.brickforceaurora.server.command.ICommandExtension;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.config.PermissionSet;
import de.brickforceaurora.server.match.config.PermissionsConfig;
import de.brickforceaurora.server.match.user.ClientActor;
import me.lauriichan.laylib.command.annotation.Action;
import me.lauriichan.laylib.command.annotation.Command;
import me.lauriichan.laylib.command.annotation.Description;
import me.lauriichan.laylib.command.annotation.Permission;
import me.lauriichan.snowframe.ConfigModule;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@Permission("server.permission")
@Command(name = "permission", description = "Permission management")
public class PermissionCommand implements ICommandExtension {

    @Action("grant")
    @Description("Grants someone a permission")
    public void grant(BFActor<MatchServerApp, ?> actor, UUID userId, String permission) {
        modifyPermissions(actor.snowFrame().module(ConfigModule.class).manager().config(PermissionsConfig.class), userId, set -> set.set(true, permission));
    }

    @Action("revoke")
    @Description("Revokes someone a permission")
    public void revoke(BFActor<MatchServerApp, ?> actor, UUID userId, String permission) {
        modifyPermissions(actor.snowFrame().module(ConfigModule.class).manager().config(PermissionsConfig.class), userId, set -> set.set(false, permission));
    }

    @Action("clear")
    @Description("Clears someones permissions")
    public void clear(BFActor<MatchServerApp, ?> actor, UUID userId) {
        modifyPermissions(actor.snowFrame().module(ConfigModule.class).manager().config(PermissionsConfig.class), userId, PermissionSet::clear);
    }

    private void modifyPermissions(PermissionsConfig config, UUID userId, Consumer<PermissionSet> modifier) {
        ClientActor actor = ClientActor.of(userId);
        PermissionSet set;
        if (actor != null) {
            set = actor.permissionSet();
        } else {
            config.load(set = new PermissionSet(userId));
        }
        modifier.accept(set);
        if (actor == null) {
            config.update(set);
        }
    }
    
}
