package de.brickforceaurora.gameserver.net.listener;

import de.brickforceaurora.gameserver.channel.Channel;
import de.brickforceaurora.gameserver.channel.ChannelMode;
import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.INetListener;
import de.brickforceaurora.gameserver.net.NetContext;
import de.brickforceaurora.gameserver.net.PacketHandler;
import de.brickforceaurora.gameserver.net.protocol.clientbound.*;
import de.brickforceaurora.gameserver.net.protocol.clientbound.emulator.ClientboundEmulatorInventoryPacket;
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
            .xp(client.data().xp)
            .tutorialFlag((byte) 0b11)  // All tutorials done
            .country(-1)                // We don't really want to set a country
            .tosAccepted(true)          // Everyone accepted ToS.
            .extraSlots(client.data().extraSlots)
            .rank(client.data().rank)
            .firstLoginForcePoints(0)); // They basically have infinite FP so we don't need that
        client.send(new ClientboundChannelPacket().channels(new Channel[]{
                new Channel(1, ChannelMode.BATTLE, "Play", "", 5000, 1, 16, 1, 0, 66, 0, 0, 0)
        }));
        client.send(new ClientboundCurChannelPacket().channelId(1)); //send actual channel
        client.send(new ClientboundEmulatorInventoryPacket());
        client.send(new ClientboundLoginPacket().clientId(client.id()).channelId(1)); //send actual channelid
        client.send(new ClientboundPlayerInfoPacket()
            .name(client.name())
            .playerXp(client.data().xp)
            .forcePoints(100_000_000)
            .brickPoints(100_000_000)
            .tokens(100_000_000)
            .unused(0)
            .coins(100_000_000)
            .starDust(100_000_000)
            .apsType(6)
            .apsLevel(5)
            .gm(0)
            .clanId(0)
            .clanName("Clan")
            .clanMark(0)
            .clanLv(0)
            .rank(client.data().rank)
            .heavy(0)
            .assault(0)
            .sniper(0)
            .subMachine(0)
            .handGun(0)
            .melee(0)
            .special(0));
        //send all maps client.send(new ClientboundDownloadMapPacket());
        client.send(new ClientboundUserMapPacket()
            .page(-1)
            .count(0)
            .slot(0)
            .alias("")
            .brickCount(-1)
            .year(2000)
            .month((byte) 0)
            .day((byte) 0)
            .hour((byte) 0)
            .minute((byte) 0)
            .second((byte) 0)
            .premium((byte) 0));
        //send all user maps client.send(new ClientboundUserMapPacket());
    }

    @PacketHandler
    public void onHeartbeat(NetContext<ServerboundHeartbeatPacket> context) {
        context.manager().keepClientAlive(context.client());
    }

}
