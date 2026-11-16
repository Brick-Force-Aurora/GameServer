package de.brickforceaurora.server.match.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.InventoryItem;
import de.brickforceaurora.server.database.model.UserAccount;
import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.database.model.UserStats;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.item.Inventories;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetContext;
import de.brickforceaurora.server.net.NetSignal;
import de.brickforceaurora.server.net.PacketHandler;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundChannelPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundCurChannelPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundIAgreeTosPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundItemListPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundLoginPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPlayerInfoPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPlayerInitInfoPacket;
import de.brickforceaurora.server.net.protocol.clientbound.original.ClientboundPlayerOptPacket;
import de.brickforceaurora.server.net.protocol.data.ChannelInfo;
import de.brickforceaurora.server.net.protocol.data.ChannelMode;
import de.brickforceaurora.server.net.protocol.data.CommonOption;
import de.brickforceaurora.server.net.protocol.data.CountryFilter;
import de.brickforceaurora.server.net.protocol.data.GameMode;
import de.brickforceaurora.server.net.protocol.data.ItemUsage;
import de.brickforceaurora.server.net.protocol.data.QuickPlayOption;
import de.brickforceaurora.server.net.protocol.data.Tutorial;
import de.brickforceaurora.server.net.protocol.serverbound.original.ServerboundIAgreeTosPacket;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;

@Extension
public class ClientListener_ implements ISignalHandler {

    public static final String ATTR_ACCOUNT_ID = "AccountId";
    // The profile is loaded once at the login, whoever changes it has to save it and replace this attribute
    public static final String ATTR_PROFILE = "Profile";

    private static final IFlags<GameMode> NO_GAMEMODES = GameMode.MANAGER.newImmutable(0);
    private static final IFlags<CommonOption> DEFAULT_COMMON_OPTIONS = CommonOption.MANAGER.newMutable()
        .setAll(true, CommonOption.DONOT_NEWBIE_CHANNEL_MSG, CommonOption.DONOT_BUNGEE_GUIDE, CommonOption.DONOT_MAPEDIT_GUIDE,
            CommonOption.DONOT_BND_GUIDE, CommonOption.DONOT_EXPLOSION_ATTACK_GUIDE, CommonOption.DONOT_EXPLOSION_DEFENCE_GUIDE,
            CommonOption.DONOT_BATTLE_GUIDE, CommonOption.DONOT_ZOMBIE_GUIDE, CommonOption.DONOT_FLAG_GUIDE,
            CommonOption.DONOT_DEFENSE_GUIDE, CommonOption.DONOT_ESCAPE_GUIDE)
        .immutable();

    private final ISimpleLogger logger;
    private final MatchServerApp app;

    public ClientListener_(SnowFrame<MatchServerApp> snowFrame) {
        this.logger = snowFrame.logger();
        this.app = snowFrame.app();
    }

    @SignalHandler
    public void onLogin(final SignalContext<NetSignal.ClientLoggedIn> context) {
        final BFClient client = context.signal().client();

        UserProfile profile;
        UserStats stats;
        List<InventoryItem> inventory;
        try {
            final UserRepository users = app.userRepository();
            // The login handler already authenticated the client and created the account if needed
            final Optional<UserAccount> account = users.findAccount(client.name());
            if (account.isEmpty()) {
                logger.warning("No account for '{0}' ({1})", client.name(), client.ip());
                client.disconnect("Account is not available");
                return;
            }
            final int accountId = account.get().id();
            client.attrSet(ATTR_ACCOUNT_ID, accountId);
            profile = users.loadProfile(accountId).orElseThrow();
            stats = users.loadStats(accountId).orElseThrow();
            inventory = users.loadInventory(accountId);
            logger.info("Loaded account '{0}' (id {1}): xp {2}, rank {3}, {4} item(s)", client.name(), accountId, profile.xp(),
                profile.rank(), inventory.size());
        } catch (final DatabaseException exception) {
            logger.error("Failed to load account of '{0}' ({1})", exception, client.name(), client.ip());
            client.disconnect("Server Error: Couldn't load account");
            return;
        }

        // The nickname is only set once the character was created, until then the login name is used
        final String nickname = profile.hasCharacter() ? profile.nickname() : client.name();
        final CountryFilter countryFilter = CountryFilter.byId(profile.countryFilter());
        final List<ClientboundItemListPacket.ItemEntry> items = new ArrayList<>(inventory.size());
        for (final InventoryItem item : inventory) {
            final ItemUsage usage = ItemUsage.byId(item.usage());
            items.add(new ClientboundItemListPacket.ItemEntry(item.id(), item.code(), usage == null ? ItemUsage.NOT_USING : usage,
                item.remain(), (byte) item.premium(), item.durability()));
        }

        client.attrSet(ATTR_PROFILE, profile);
        client.attrSet(Inventories.ATTR_INVENTORY, List.copyOf(inventory));

        final int users = Math.max(1, (int) context.signal().netManager().activeClients().count());

        //TODO: Hardcoded info needs to change
        client.send(new ClientboundChannelPacket().channels(new ChannelInfo[] {
            new ChannelInfo(1, ChannelMode.BATTLE, "Play", "127.0.0.1", 18890, users, 16, 1, 0, 66, 0, 0, 0)
        }), new ClientboundCurChannelPacket().curChannelId(1),
            new ClientboundPlayerInitInfoPacket().xp(profile.xp()).tutorials(Tutorial.MANAGER.newImmutable(profile.tutorials()))
                .tosAccepted(profile.tosAccepted()).extraSlots(profile.extraSlots()).firstLoginFp(profile.firstLoginFp())
                .countryFilter(countryFilter == null ? CountryFilter.NONE : countryFilter).rank(profile.rank()),
            new ClientboundPlayerOptPacket().modeOptions(NO_GAMEMODES).quickPlayOption(QuickPlayOption.ALL_MAPS)
                .commonOptions(DEFAULT_COMMON_OPTIONS),
            new ClientboundPlayerInfoPacket().nickname(nickname).xp(profile.xp()).forcePoints(profile.point()).brickPoints(profile.brick())
                .tokens(profile.cash()).coins(profile.freeCoin()).starDust(profile.starDust()).gm(0).clanLv(-1).clanMark(-1).clanSeq(-1)
                .clanName("").assault(stats.assault()).heavy(stats.heavy()).handGun(stats.handGun()).sniper(stats.sniper())
                .melee(stats.melee()).special(stats.special()).subMachine(stats.subMachine()).apsType(6).apsLevel(5).rank(profile.rank()),
            new ClientboundLoginPacket().clientId(client.id()).channelId(1), new ClientboundItemListPacket().items(items));
        client.send(Inventories.slotPackets(inventory));
    }

    @PacketHandler
    public void onIAgreeTos(final NetContext<ServerboundIAgreeTosPacket> context) {
        final BFClient client = context.client();

        final Integer accountId = client.attr(ATTR_ACCOUNT_ID, Integer.class);
        if (accountId == null) {
            logger.warning("'{0}' ({1}) agreed to the TOS without being logged in", client.name(), client.ip());
            return;
        }
        try {
            final UserRepository users = app.userRepository();
            if (!users.acceptTos(accountId)) {
                logger.warning("Couldn't save TOS agreement of '{0}': Account {1} has no profile", client.name(), accountId);
                return;
            }
            users.loadProfile(accountId).ifPresent(profile -> client.attrSet(ATTR_PROFILE, profile));
        } catch (final DatabaseException exception) {
            logger.error("Failed to save TOS agreement of '{0}' ({1})", exception, client.name(), client.ip());
            client.disconnect("Server Error: Couldn't save TOS agreement");
            return;
        }
        logger.info("'{0}' (id {1}) agreed to the TOS", client.name(), accountId);
        client.send(new ClientboundIAgreeTosPacket());
    }

}
