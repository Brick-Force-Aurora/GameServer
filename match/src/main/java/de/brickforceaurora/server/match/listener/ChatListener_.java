package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.command.Console;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.user.ClientActor;
import de.brickforceaurora.server.match.util.ChatMessage;
import de.brickforceaurora.server.match.util.TextFormatter;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.INetListener;
import de.brickforceaurora.server.net.tcp.NetContext;
import de.brickforceaurora.server.net.tcp.PacketHandler;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundChatPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundWhisperPacket;
import de.brickforceaurora.server.net.tcp.protocol.serverbound.original.ServerboundTeamChatPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class ChatListener_ implements INetListener {

    @PacketHandler
    public void onChat(final NetContext<ServerboundChatPacket> context) {
        final String text = TextFormatter.clean(context.packet().text());
        if (text == null) {
            return;
        }
        BFClient sender = context.client();
        if (text.startsWith("/") && Console.INSTANCE.execute(ClientActor.of(sender), text)) {
            return;
        }
        ChatMessage message = ChatMessage.normal(sender).appendClean(text);
        Room room = sender.attr(Room.ATTR_ROOM, Room.class);
        if (room != null) {
            message.broadcastRoom(room);
            return;
        }
        message.broadcastChannel(context.manager());
    }

    @PacketHandler
    public void onTeamChat(final NetContext<ServerboundTeamChatPacket> context) {
        final String text = TextFormatter.clean(context.packet().text());
        if (text == null) {
            return;
        }
        BFClient sender = context.client();
        if (text.startsWith("/") && Console.INSTANCE.execute(ClientActor.of(sender), text)) {
            return;
        }
        Room room = sender.attr(Room.ATTR_ROOM, Room.class);
        if (room == null) {
            return;
        }
        ChatMessage.team(sender).appendClean(text).broadcastRoomTeam(room, sender);
    }

    @PacketHandler
    public void onWhisper(final NetContext<ServerboundWhisperPacket> context) {
        final String text = TextFormatter.clean(context.packet().text());
        if (text == null) {
            return;
        }
        // Whispers are a command and therefore should not contain command handling
        BFClient sender = context.client();
        BFClient target = context.manager().activeClients().filter(other -> other.name().equals(context.packet().listener())).findFirst()
            .orElse(null);
        if (target == null) {
            ChatMessage.system().appendFormat("The user '{}' is not online.", context.packet().listener()).send(sender);
            return;
        }
        ChatMessage.whisper(sender).appendClean(text).send(sender, target);
    }

}
