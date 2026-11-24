package de.brickforceaurora.server.match.user;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.config.PermissionsConfig;
import de.brickforceaurora.server.net.tcp.NetSignal;
import me.lauriichan.snowframe.ConfigModule;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public final class UserSignalHandler_ implements ISignalHandler {
    
    private final PermissionsConfig permissionsConfig;

    public UserSignalHandler_(SnowFrame<MatchServerApp> snowFrame) {
        this.permissionsConfig = snowFrame.module(ConfigModule.class).manager().config(PermissionsConfig.class);
    }

    @SignalHandler
    public void onClientDisconnect(final SignalContext<NetSignal.ClientDisconnected> context) {
        ClientActor actor = ClientActor.of(context.signal().client());
        if (actor == null) {
            return;
        }
        ClientActor.unregister(actor.getId());
        permissionsConfig.update(actor.permissionSet());
    }
    
}
