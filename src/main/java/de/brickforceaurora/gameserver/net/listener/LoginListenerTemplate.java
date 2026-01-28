package de.brickforceaurora.gameserver.net.listener;

import java.time.LocalDateTime;

import de.brickforceaurora.gameserver.channel.Channel;
import de.brickforceaurora.gameserver.channel.ChannelManager;
import de.brickforceaurora.gameserver.legacy.channel.ChannelMode;
import de.brickforceaurora.gameserver.legacy.maps.RegMap;
import de.brickforceaurora.gameserver.net.BFClient;
import de.brickforceaurora.gameserver.net.INetListener;
import de.brickforceaurora.gameserver.net.NetContext;
import de.brickforceaurora.gameserver.net.PacketHandler;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundChannelPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundCurChannelPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundLoginPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundPlayerInfoPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundPlayerInitInfoPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundRoaminPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.ClientboundUserMapPacket;
import de.brickforceaurora.gameserver.net.protocol.clientbound.emulator.ClientboundEmulatorInventoryPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundHeartbeatPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundLoginPacket;
import de.brickforceaurora.gameserver.net.protocol.serverbound.ServerboundRoaminPacket;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class LoginListenerTemplate implements INetListener {

    private final ChannelManager channelManager;

    public LoginListenerTemplate(final ChannelManager channelManager) {
        this.channelManager = channelManager;
    }

    @PacketHandler
    public void onLogin(final NetContext<ServerboundLoginPacket> context) {
        final ServerboundLoginPacket packet = context.packet();
        final BFClient client = context.client();

        // Initialize client
        client.init(packet.id(), context.manager().nextClientId.getAndIncrement());

        // Now send everything
        client.send(new ClientboundPlayerInitInfoPacket().xp(client.data().xp).tutorialFlag((byte) 0b11)  // All tutorials done
            .country(-1)                // We don't really want to set a country
            .tosAccepted(true)          // Everyone accepted ToS.
            .extraSlots(client.data().extraSlots).rank(client.data().rank).firstLoginForcePoints(0)); // They basically have infinite FP so we don't need that
        client.send(new ClientboundChannelPacket().channels(channelManager.channels().toArray(Channel[]::new)));
        final int channelId = channelManager.channels(ChannelMode.BATTLE).get(0).id();
        client.send(new ClientboundCurChannelPacket().channelId(channelId)); //send actual channel
        client.send(new ClientboundEmulatorInventoryPacket());
        client.send(new ClientboundLoginPacket().channelId(channelId)); //send actual channelid
        client.send(new ClientboundPlayerInfoPacket().name(client.name()).playerXp(client.data().xp).forcePoints(100_000_000)
            .brickPoints(100_000_000).tokens(100_000_000).unused(0).coins(100_000_000).starDust(100_000_000).apsType(6).apsLevel(5).gm(0)
            .clanId(0).clanName("Clan").clanMark(0).clanLv(0).rank(client.data().rank).heavy(0).assault(0).sniper(0).subMachine(0)
            .handGun(0).melee(0).special(0));
        //send all maps client.send(new ClientboundDownloadMapPacket());
        client.send(new ClientboundUserMapPacket().page(-1).offset(0).count(1).regMaps(new RegMap[] {
            new RegMap(0, "", "", LocalDateTime.of(2000, 1, 1, 0, 0, 0), 0, true, false, 0, 0, 0, 0, 0, 0, (byte) 0, false)
        }));
        //send all user maps client.send(new ClientboundUserMapPacket());
    }

    @PacketHandler
    public void onHeartbeat(final NetContext<ServerboundHeartbeatPacket> context) {
        context.manager().keepClientAlive(context.client());
    }

    @PacketHandler
    public void onRoamIn(final NetContext<ServerboundRoaminPacket> context) {
        final BFClient client = context.client();

        //ChannelHandlers.SendUserList(server, msgRef.client);
        client.send(new ClientboundRoaminPacket().channelDestinationId(1));
        //client.clientStatus = ClientStatus.LOBBY;
    }

}
