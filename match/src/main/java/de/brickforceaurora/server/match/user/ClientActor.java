package de.brickforceaurora.server.match.user;

import java.util.UUID;

import de.brickforceaurora.server.command.BFActor;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.config.PermissionSet;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.protocol.clientbound.original.ClientboundChatPacket;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import me.lauriichan.snowframe.SnowFrame;

public final class ClientActor extends BFActor<MatchServerApp, BFClient> {
    
    private static final Object2ObjectMap<UUID, ClientActor> UUID2ACTOR = Object2ObjectMaps.synchronize(new Object2ObjectArrayMap<>());

    static void unregister(UUID uuid) {
        UUID2ACTOR.remove(uuid);
    }
    
    public static ClientActor of(UUID uuid) {
        return UUID2ACTOR.get(uuid);
    }
    
    public static ClientActor of(BFClient client) {
        return client.attr("Actor", ClientActor.class);
    }

    // ONLY USE IF U DON'T NEED THE CLIENT ACTOR FOR ANYTHING ELSE
    public static UUID uuidOf(BFClient client) {
        ClientActor actor = of(client);
        if (actor == null) {
            return null;
        }
        return actor.getId();
    }
    
    // ONLY USE IF U DON'T NEED THE CLIENT ACTOR FOR ANYTHING ELSE
    public static boolean isGameMaster(BFClient client) {
        ClientActor actor = of(client);
        if (actor == null) {
            return false;
        }
        return actor.isGameMaster();
    }
    
    private final UUID uuid;
    private final PermissionSet permissionSet;

    ClientActor(SnowFrame<MatchServerApp> snowFrame, BFClient handle, UUID uuid) {
        super(snowFrame, handle);
        this.uuid = uuid;
        this.permissionSet = new PermissionSet(uuid);
        handle.attrSet("Actor", this);
        UUID2ACTOR.put(uuid, this);
    }
    
    public PermissionSet permissionSet() {
        return permissionSet;
    }
    
    public boolean isGameMaster() {
        return permissionSet.has("game.admin");
    }

    @Override
    public UUID getId() {
        return uuid;
    }

    @Override
    public String getName() {
        return handle.name();
    }
    
    @Override
    public boolean hasPermission(String permission) {
        return permissionSet.has(permission);
    }
    
    @Override
    public void sendMessage(String message) {
        handle.send(new ClientboundChatPacket().text(message));
    }

}
