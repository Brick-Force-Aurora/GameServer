package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.ChannelInfo;
import de.brickforceaurora.server.net.protocol.data.ChannelMode;
import de.brickforceaurora.server.net.protocol.data.CommonOption;
import de.brickforceaurora.server.net.protocol.data.CountryFilter;
import de.brickforceaurora.server.net.protocol.data.GameMode;
import de.brickforceaurora.server.net.protocol.data.QuickPlayOption;
import de.brickforceaurora.server.net.protocol.data.Tutorial;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public class ClientListener_ implements ISignalHandler {

    private static final IFlags<GameMode> NO_GAMEMODES = GameMode.MANAGER.newImmutable(0);
    private static final IFlags<CommonOption> DEFAULT_COMMON_OPTIONS = CommonOption.MANAGER.newMutable().setAll(true, new CommonOption[] {
        CommonOption.DONOT_NEWBIE_CHANNEL_MSG,
        CommonOption.DONOT_BUNGEE_GUIDE,
        CommonOption.DONOT_MAPEDIT_GUIDE,
        CommonOption.DONOT_BND_GUIDE,
        CommonOption.DONOT_EXPLOSION_ATTACK_GUIDE,
        CommonOption.DONOT_EXPLOSION_DEFENCE_GUIDE,
        CommonOption.DONOT_BATTLE_GUIDE,
        CommonOption.DONOT_ZOMBIE_GUIDE,
        CommonOption.DONOT_FLAG_GUIDE,
        CommonOption.DONOT_DEFENSE_GUIDE,
        CommonOption.DONOT_ESCAPE_GUIDE
    }).immutable();
    private static final IFlags<Tutorial> TUTORIALS_DONE = Tutorial.MANAGER.newMutable().setAll(true, Tutorial.BATTLE, Tutorial.BUILD)
        .immutable();

    @SignalHandler
    public void onLogin(SignalContext<NetSignal.ClientLoggedIn> context) {
        BFClient client = context.signal().client();
        client.send(new IClientboundPacket[] {
            new ClientboundChannelPacket().channels(new ChannelInfo[] {
                new ChannelInfo(1, ChannelMode.BATTLE, "Play", "127.0.0.1", 18890, 1, 16, 1, 0, 66, 0, 0, 0)
            }),
            new ClientboundCurChannelPacket().curChannelId(1),
            new ClientboundPlayerInitInfoPacket().xp(9400000).tutorials(TUTORIALS_DONE).tosAccepted(true).extraSlots(9).firstLoginFp(0)
                .countryFilter(CountryFilter.NONE).rank(65),
            new ClientboundPlayerOptPacket().modeOptions(NO_GAMEMODES).quickPlayOption(QuickPlayOption.ALL_MAPS)
                .commonOptions(DEFAULT_COMMON_OPTIONS),
            new ClientboundPlayerInfoPacket().nickname(client.name()).xp(9400000).forcePoints(100000000).brickPoints(0).tokens(100000000)
                .coins(100000000).starDust(0).gm(0).clanLv(0).clanMark(0).clanSeq(0).clanName("Clan").assault(0).heavy(0).handGun(0)
                .sniper(0).melee(0).special(0).subMachine(0).apsType(6).apsLevel(5).rank(65),
            new ClientboundLoginPacket().clientId(client.id()).channelId(1)
        });
    }

}
