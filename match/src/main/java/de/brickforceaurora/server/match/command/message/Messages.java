package de.brickforceaurora.server.match.command.message;

import de.brickforceaurora.server.command.message.IMessageExtension;
import me.lauriichan.laylib.localization.MessageProvider;
import me.lauriichan.laylib.localization.source.Message;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public final class Messages implements IMessageExtension {

    private Messages() {
        throw new UnsupportedOperationException();
    }
    
    
    @Message(id = "command.metrics.players.none", content = "There are no players online right now.")
    public static MessageProvider COMMAND_METRICS_PLAYERS_NONE;
    @Message(id = "command.metrics.players.header", content = "There are $count player(s) online:")
    public static MessageProvider COMMAND_METRICS_PLAYERS_HEADER;
    @Message(id = "command.metrics.players.entry", content = "- $name ($id)")
    public static MessageProvider COMMAND_METRICS_PLAYERS_ENTRY;
    
    @Message(id = "command.metrics.rooms.none", content = "There are no rooms open right now.")
    public static MessageProvider COMMAND_METRICS_ROOMS_NONE;
    @Message(id = "command.metrics.rooms.header", content = "There are $count room(s) open:")
    public static MessageProvider COMMAND_METRICS_ROOMS_HEADER;
    @Message(id = "command.metrics.rooms.entry", content = "[$id] '$title' ($type, $status): $client.count / $client.max_count player(s); goal: $goal")
    public static MessageProvider COMMAND_METRICS_ROOMS_ENTRY;

}
