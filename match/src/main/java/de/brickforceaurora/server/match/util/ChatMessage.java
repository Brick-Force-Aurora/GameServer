package de.brickforceaurora.server.match.util;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.game.TeamGameData;
import de.brickforceaurora.server.match.game.TeamGameMode;
import de.brickforceaurora.server.match.room.Room;
import de.brickforceaurora.server.match.user.ClientActor;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.NetManager;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundChatPacket;
import de.brickforceaurora.server.net.tcp.protocol.data.ChatType;
import me.lauriichan.laylib.command.Actor;
import me.lauriichan.laylib.localization.IMessage;
import me.lauriichan.laylib.localization.Key;
import me.lauriichan.laylib.localization.MessageManager;
import me.lauriichan.laylib.localization.MessageProvider;
import me.lauriichan.laylib.logger.util.StringUtil;

public final class ChatMessage {

    public static ChatMessage system() {
        return new ChatMessage(ChatType.SYSTEM, -1, "System", false);
    }

    public static ChatMessage system(String name) {
        return new ChatMessage(ChatType.SYSTEM, -1, name, false);
    }

    public static ChatMessage treasure() {
        return new ChatMessage(ChatType.TREASURE, -1, "Treasure", false);
    }

    public static ChatMessage treasure(String name) {
        return new ChatMessage(ChatType.TREASURE, -1, name, false);
    }

    public static ChatMessage normal(BFClient sender) {
        return new ChatMessage(ChatType.NORMAL, sender.id(), sender.name(), ClientActor.isGameMaster(sender));
    }

    public static ChatMessage normal(String name, boolean gameMaster) {
        return new ChatMessage(ChatType.NORMAL, -1, name, gameMaster);
    }

    public static ChatMessage team(BFClient sender) {
        return new ChatMessage(ChatType.TEAM, sender.id(), sender.name(), ClientActor.isGameMaster(sender));
    }

    public static ChatMessage team(String name, boolean gameMaster) {
        return new ChatMessage(ChatType.TEAM, -1, name, gameMaster);
    }

    public static ChatMessage whisper(BFClient sender) {
        return new ChatMessage(ChatType.WHISPER, sender.id(), sender.name(), ClientActor.isGameMaster(sender));
    }

    public static ChatMessage whisper(String name, boolean gameMaster) {
        return new ChatMessage(ChatType.WHISPER, -1, name, gameMaster);
    }

    public static ChatMessage clan(BFClient sender) {
        return new ChatMessage(ChatType.CLAN, sender.id(), sender.name(), ClientActor.isGameMaster(sender));
    }

    public static ChatMessage clan(String name, boolean gameMaster) {
        return new ChatMessage(ChatType.CLAN, -1, name, gameMaster);
    }

    // I know this is horrible but its the best solution here without overcomplicating the api
    private static final MessageManager messageManager = MatchServerApp.get().app().messageManager();

    private final int clientId;
    private final ChatType chatType;
    private final String name;
    private final boolean gameMaster;

    private StringBuilder message = new StringBuilder();

    private ChatMessage(ChatType chatType, int clientId, String name, boolean gameMaster) {
        this.chatType = chatType;
        this.clientId = clientId;
        this.name = name;
        this.gameMaster = gameMaster;
    }

    public String message() {
        return message.toString();
    }

    public ChatMessage append(String string) {
        message.append(string);
        return this;
    }

    public ChatMessage appendClean(String string) {
        TextFormatter.appendClean(message, string);
        return this;
    }

    public ChatMessage appendFormat(String format, Object... values) {
        message.append(StringUtil.format(format, values));
        return this;
    }

    public ChatMessage appendMessage(String messageId, Key... placeholders) {
        message.append(messageManager.translate(messageId, Actor.DEFAULT_LANGUAGE, placeholders));
        return this;
    }

    public ChatMessage appendMessage(IMessage message, Key... placeholders) {
        this.message.append(messageManager.format(message, placeholders));
        return this;
    }

    public ChatMessage appendMessage(MessageProvider messageProvider, Key... placeholders) {
        message.append(messageManager.translate(messageProvider, Actor.DEFAULT_LANGUAGE, placeholders));
        return this;
    }

    public ChatMessage clear() {
        message.setLength(0);
        return this;
    }
    
    public ClientboundChatPacket build() {
        return new ClientboundChatPacket(clientId, chatType, name, gameMaster, message.toString());
    }

    public void broadcastRoom(Room room) {
        ClientboundChatPacket pkt = build();
        for (BFClient client : room.clients()) {
            client.send(pkt);
        }
    }

    public void broadcastRoomTeam(Room room, BFClient sender) {
        if (!(room.mode() instanceof TeamGameMode)) {
            broadcastRoom(room);
            return;
        }
        broadcastTeam(room, TeamGameData.isRed(slotOf(sender)));
    }

    public void broadcastRoomTeam(Room room, boolean redTeam) {
        if (!(room.mode() instanceof TeamGameMode)) {
            broadcastRoom(room);
            return;
        }
        broadcastTeam(room, redTeam);
    }
    
    private void broadcastTeam(Room room, boolean redTeam) {
        ClientboundChatPacket pkt = build();
        for (BFClient client : room.clients()) {
            if (TeamGameData.isRed(slotOf(client)) == redTeam) {
                client.send(pkt);
            }
        }
    }

    public void broadcastChannel(NetManager<?> netManager) {
        ClientboundChatPacket pkt = build();
        netManager.activeClients().filter(client -> !client.attrHas(Room.ATTR_ROOM)).forEach(client -> client.send(pkt));
    }

    public void send(BFClient client) {
        client.send(build());
    }

    public void send(BFClient... clients) {
        ClientboundChatPacket pkt = build();
        for (BFClient client : clients) {
            client.send(pkt);
        }
    }

    private int slotOf(final BFClient client) {
        final Integer slot = client.attr(Room.ATTR_SLOT, Integer.class);
        return slot == null ? 0 : slot;
    }

}
