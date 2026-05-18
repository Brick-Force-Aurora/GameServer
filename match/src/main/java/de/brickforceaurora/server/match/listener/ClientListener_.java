package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.ChannelInfo;
import de.brickforceaurora.server.net.protocol.data.ChannelMode;
import de.brickforceaurora.server.net.protocol.data.CommonOpt;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public class ClientListener_ implements ISignalHandler {

    public static final int QJ_COMMON_MASK =
            CommonOpt.DONOT_NEWBIE_CHANNEL_MSG.mask()
                    | CommonOpt.DONOT_BUNGEE_GUIDE.mask()
                    | CommonOpt.DONOT_MAPEDIT_GUIDE.mask()
                    | CommonOpt.DONOT_BND_GUIDE.mask()
                    | CommonOpt.DONOT_EXPLOSION_ATTACK_GUIDE.mask()
                    | CommonOpt.DONOT_EXPLOSION_DEFENCE_GUIDE.mask()
                    | CommonOpt.DONOT_BATTLE_GUIDE.mask()
                    | CommonOpt.DONOT_ZOMBIE_GUIDE.mask()
                    | CommonOpt.DONOT_FLAG_GUIDE.mask()
                    | CommonOpt.DONOT_DEFENSE_GUIDE.mask()
                    | CommonOpt.DONOT_ESCAPE_GUIDE.mask();

    @SignalHandler
    public void onLogin(SignalContext<NetSignal.ClientLoggedIn> context) {
        BFClient client = context.signal().client();
        client.send(new IClientboundPacket[] {
            new ClientboundChannelPacket().channels(new ChannelInfo[]{ new ChannelInfo(1, ChannelMode.BATTLE.mask(), "Play", "127.0.0.1", 18890, 1, 16, 1, 0, 66, 0, 0, 0)}),
            new ClientboundCurChannelPacket().curChannelId(1),
            new ClientboundPlayerInitInfoPacket().xp(9400000)
            .tutorialed(ClientboundPlayerInitInfoPacket.TUTORIALS_DONE) // TODO: Flags
            .tos(ClientboundPlayerInitInfoPacket.TOS_ACCEPTED) // TODO: Boolean
            .extraSlots(9).firstLoginFp(0).countryFilter(-1).rank(65),
            new ClientboundPlayerOptPacket().qjModeMask(0).qjOfficialMask(0).qjCommonMask(QJ_COMMON_MASK),
            new ClientboundPlayerInfoPacket().nickname(client.name()).xp(9400000).forcePoints(100000000).brickPoints(0)
            .tokens(100000000).coins(100000000).starDust(0).gm(0).clanLv(0).clanMark(0).clanSeq(0).clanName("Clan")
            .assault(0).heavy(0).handGun(0).sniper(0).melee(0).special(0).subMachine(0).apsType(6).apsLevel(5).rank(65),
            new ClientboundLoginPacket().clientId(client.id()).channelId(1)
        });
    }

}
