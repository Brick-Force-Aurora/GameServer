package de.brickforceaurora.server.match.command;

import de.brickforceaurora.server.command.ICommandExtension;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.command.message.Messages;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.protocol.data.api.IRoomInfo;
import me.lauriichan.laylib.command.Actor;
import me.lauriichan.laylib.command.annotation.Action;
import me.lauriichan.laylib.command.annotation.Command;
import me.lauriichan.laylib.command.annotation.Permission;
import me.lauriichan.laylib.localization.Key;
import me.lauriichan.snowframe.extension.Extension;

@Extension
@Permission("server.metrics")
@Command(name = "metrics", description = "Shows metrics about the server")
public class MetricsCommand implements ICommandExtension {

    private final MatchServerApp app = MatchServerApp.get().app();

    @Action("clients")
    public void clients(Actor<?> actor) {
        if (app.server().netManager().clientCount() == 0) {
            actor.sendTranslatedMessage(Messages.COMMAND_METRICS_PLAYERS_NONE);
            return;
        }
        BFClient[] clients = app.server().netManager().activeClients().toArray(BFClient[]::new);
        actor.sendTranslatedMessage(Messages.COMMAND_METRICS_PLAYERS_HEADER, Key.of("count", clients.length));
        for (BFClient client : clients) {
            actor.sendTranslatedMessage(Messages.COMMAND_METRICS_PLAYERS_ENTRY, Key.of("name", client.name()), Key.of("id", client.id()));
        }
    }

    @Action("rooms")
    public void rooms(Actor<?> actor) {
        if (app.roomManager().roomCount() == 0) {
            actor.sendTranslatedMessage(Messages.COMMAND_METRICS_ROOMS_NONE);
            return;
        }
        IRoomInfo[] infos = app.roomManager().roomInfos();
        actor.sendTranslatedMessage(Messages.COMMAND_METRICS_PLAYERS_HEADER, Key.of("count", infos.length));
        for (IRoomInfo info : infos) {
            actor.sendTranslatedMessage(Messages.COMMAND_METRICS_PLAYERS_ENTRY, Key.of("id", info.id()), Key.of("title", info.title()),
                Key.of("status", info.status()), Key.of("client.count", info.players()), Key.of("client.max_count", info.maxPlayers()),
                Key.of("goal", info.goal()));
        }
    }

}
