package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public class ClientListener_ implements ISignalHandler {

    @SignalHandler
    public void onLogin(SignalContext<NetSignal.ClientLoggedIn> context) {
        BFClient client = context.signal().client();
        client.send(new IClientboundPacket[] {
            new ClientboundChannelPacket(),
            new ClientboundCurChannelPacket().curChannelId(1),
            new ClientboundPlayerInitInfoPacket().xp(9400000)
            .tutorialed(ClientboundPlayerInitInfoPacket.TUTORIALS_DONE) // TODO: Flags
            .tos(ClientboundPlayerInitInfoPacket.TOS_ACCEPTED) // TODO: Boolean
            .extraSlots(9).firstLoginFp(0).countryFilter(-1).rank(65),
            new ClientboundPlayerOptPacket().qjModeMask(0).qjOfficialMask(0).qjCommonMask(31807),
            new ClientboundPlayerInfoPacket().nickname(client.name()).xp(9400000).forcePoints(100000000).brickPoints(0)
            .tokens(100000000).coins(100000000).starDust(0).gm(0).clanLv(0).clanMark(0).clanSeq(0).clanName("Clan")
            .assault(0).heavy(0).handGun(0).sniper(0).melee(0).special(0).subMachine(0).apsType(6).apsLevel(5).rank(65),
            new ClientboundLoginPacket().clientId(client.id()).channelId(1)
        });
    }

}
