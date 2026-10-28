package de.brickforceaurora.server.match.listener;

import de.brickforceaurora.server.database.Database;
import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.LoginRepository;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.InventoryItem;
import de.brickforceaurora.server.database.model.UserAccount;
import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.database.model.UserStats;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.IClientboundPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.*;
import de.brickforceaurora.server.net.protocol.data.*;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundIAgreeTosPacket;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.brickforceaurora.server.match.MatchServerApp.database;

@Extension
public class ClientListener_ implements ISignalHandler {

    public static final String ATTR_ACCOUNT_ID = "AccountId";

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
        ISimpleLogger logger = MatchServerApp.logger();

        UserProfile profile;
        UserStats stats;
        List<InventoryItem> inventory;
        try {
            Database database = database();
            UserRepository users = new UserRepository(database);
            //TODO: Only debug logins exist right now, this finds the account or creates it
            Optional<UserAccount> account = new LoginRepository(database).loginDebug(client.name());
            if (account.isEmpty()) {
                logger.warning("No account for '{0}' ({1}): Account is protected by a password or OAuth identity", client.name(),
                        client.ip());
                client.disconnect("Account is not available");
                return;
            }
            int accountId = account.get().id();
            client.attrSet(ATTR_ACCOUNT_ID, accountId);
            profile = users.loadProfile(accountId).orElseThrow();
            stats = users.loadStats(accountId).orElseThrow();
            inventory = users.loadInventory(accountId);
            logger.info("Loaded account '{0}' (id {1}): xp {2}, rank {3}, {4} item(s)", client.name(), accountId, profile.xp(),
                    profile.rank(), inventory.size());
        } catch (DatabaseException exception) {
            logger.error("Failed to load account of '{0}' ({1})", exception, client.name(), client.ip());
            client.disconnect("Server Error: Couldn't load account");
            return;
        }

        // The nickname is only set once the character was created, until then the login name is used
        String nickname = profile.hasCharacter() ? profile.nickname() : client.name();
        CountryFilter countryFilter = CountryFilter.byId(profile.countryFilter());
        List<ClientboundItemListPacket.ItemEntry> items = new ArrayList<>(inventory.size());
        for (InventoryItem item : inventory) {
            ItemUsage usage = ItemUsage.byId(item.usage());
            items.add(new ClientboundItemListPacket.ItemEntry(item.id(), item.code(), usage == null ? ItemUsage.NOT_USING : usage,
                    item.remain(), (byte) item.premium(), item.durability()));
        }

        //TODO: Hardcoded info needs to change
        client.send(new IClientboundPacket[] {
                new ClientboundChannelPacket().channels(new ChannelInfo[] {
                        new ChannelInfo(1, ChannelMode.BATTLE, "Play", "127.0.0.1", 18890, 1, 16, 1, 0, 66, 0, 0, 0)
                }),
                new ClientboundCurChannelPacket().curChannelId(1),
                new ClientboundPlayerInitInfoPacket().xp(profile.xp()).tutorials(Tutorial.MANAGER.newImmutable(profile.tutorials()))
                        .tosAccepted(profile.tosAccepted()).extraSlots(profile.extraSlots()).firstLoginFp(profile.firstLoginFp())
                        .countryFilter(countryFilter == null ? CountryFilter.NONE : countryFilter).rank(profile.rank()),
                new ClientboundPlayerOptPacket().modeOptions(NO_GAMEMODES).quickPlayOption(QuickPlayOption.ALL_MAPS)
                        .commonOptions(DEFAULT_COMMON_OPTIONS),
                new ClientboundPlayerInfoPacket().nickname(nickname).xp(profile.xp()).forcePoints(profile.point())
                        .brickPoints(profile.brick()).tokens(profile.cash()).coins(profile.freeCoin()).starDust(profile.starDust()).gm(0)
                        .clanLv(-1).clanMark(-1).clanSeq(-1).clanName("").assault(stats.assault()).heavy(stats.heavy())
                        .handGun(stats.handGun()).sniper(stats.sniper()).melee(stats.melee()).special(stats.special())
                        .subMachine(stats.subMachine()).apsType(6).apsLevel(5).rank(profile.rank()),
                new ClientboundLoginPacket().clientId(client.id()).channelId(1),
                new ClientboundItemListPacket().items(items)
        });
    }

    @PacketHandler
    public void onIAgreeTos(final NetContext<ServerboundIAgreeTosPacket> context) {
        BFClient client = context.client();
        ISimpleLogger logger = MatchServerApp.logger();

        Integer accountId = client.attr(ClientListener_.ATTR_ACCOUNT_ID, Integer.class);
        if (accountId == null) {
            logger.warning("'{0}' ({1}) agreed to the TOS without being logged in", client.name(), client.ip());
            return;
        }
        try {
            if (!new UserRepository(database()).acceptTos(accountId)) {
                logger.warning("Couldn't save TOS agreement of '{0}': Account {1} has no profile", client.name(), accountId);
                return;
            }
        } catch (DatabaseException exception) {
            logger.error("Failed to save TOS agreement of '{0}' ({1})", exception, client.name(), client.ip());
            client.disconnect("Server Error: Couldn't save TOS agreement");
            return;
        }
        logger.info("'{0}' (id {1}) agreed to the TOS", client.name(), accountId);
        client.send(new ClientboundIAgreeTosPacket());
    }

}
