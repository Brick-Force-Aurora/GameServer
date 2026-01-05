package de.brickforceaurora.gameserver.net.listener;

import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.INetListener;
import de.brickforceaurora.gameserver.net.NetContext;
import de.brickforceaurora.gameserver.net.PacketHandler;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundPlayerInitInfoPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundHeartbeatPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundLoginPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class LoginListenerTemplate implements INetListener {

    @PacketHandler
    public void onLogin(NetContext<ServerboundLoginPacket> context) {
        ServerboundLoginPacket packet = context.packet();
        BFClient client = context.client();
        
        // Initialize client
        client.init(packet.id(), context.manager().nextClientId.getAndIncrement());
    
        // Now send everything
        client.send(new ClientboundPlayerInitInfoPacket()
            .xp(client.data().data.xp)
            .tutorialFlag((byte) 0b11)  // All tutorials done
            .country(-1)                // We don't really want to set a country
            .tosAccepted(true)          // Everyone accepted ToS.
            .extraSlots(client.data().data.extraSlots)
            .rank(client.data().data.rank)
            .firstLoginForcePoints(0)); // They basically have infinite FP so we don't need that
    }

    @PacketHandler
    public void onHeartbeat(NetContext<ServerboundHeartbeatPacket> context) {
        context.manager().keepClientAlive(context.client());
    }

}
