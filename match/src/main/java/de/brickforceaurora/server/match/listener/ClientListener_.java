package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.*;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

import java.util.ArrayList;
import java.util.List;

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
        //TODO: Hardcoded info needs to change
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
            new ClientboundLoginPacket().clientId(client.id()).channelId(1),
            new ClientboundItemListPacket().items(List.of(
                    new ClientboundItemListPacket.ItemEntry(0, "wau", ItemUsage.EQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(1, "wax", ItemUsage.EQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(2, "wba", ItemUsage.EQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(3, "wap", ItemUsage.EQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(4, "aac", ItemUsage.EQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(5, "aad", ItemUsage.EQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(6, "s07", ItemUsage.EQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(7, "sb7", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(8, "sb8", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(9, "sb9", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(10, "sc0", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(11, "sc1", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(12, "sc3", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(13, "sc4", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(14, "s34", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(15, "s43", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(16, "s78", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(17, "s79", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(18, "s22", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(19, "s06", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(20, "s05", ItemUsage.UNEQUIP, -1, (byte) 0, 1000),
                    new ClientboundItemListPacket.ItemEntry(21, "s27", ItemUsage.UNEQUIP, -1, (byte) 0, 1000)))
        });
    }

}
