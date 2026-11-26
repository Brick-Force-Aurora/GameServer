package de.brickforceaurora.server.match.user;

import java.util.Optional;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.LoginRepository;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.UserAccount;
import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.config.PermissionsConfig;
import de.brickforceaurora.server.match.util.TextFormatter;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.BrickForceServer;
import de.brickforceaurora.server.net.tcp.login.ILoginData;
import de.brickforceaurora.server.net.tcp.login.ILoginHandler;
import de.brickforceaurora.server.net.tcp.login.ILoginResult;
import de.brickforceaurora.server.net.tcp.login.LoginType;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.ConfigModule;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.util.Version;

public final class ClaimPasswordLoginHandler implements ILoginHandler {

    private static final IFlags<LoginType> supportedTypes = LoginType.MANAGER.newImmutable(LoginType.PASSWORD.mask());

    private final SnowFrame<MatchServerApp> snowFrame;

    private final BrickForceServer<MatchServerApp> server;

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    private final PermissionsConfig permissionsConfig;

    private final Version version;

    public ClaimPasswordLoginHandler(SnowFrame<MatchServerApp> snowFrame, LoginRepository loginRepository) {
        this.snowFrame = snowFrame;
        this.server = snowFrame.app().server();
        this.userRepository = snowFrame.app().userRepository();
        this.loginRepository = loginRepository;
        this.permissionsConfig = snowFrame.module(ConfigModule.class).manager().config(PermissionsConfig.class);
        this.version = snowFrame.app().version();
    }

    @Override
    public IFlags<LoginType> supportedTypes() {
        return supportedTypes;
    }

    @Override
    public ILoginResult login(final BFClient client, final Version version, final LoginType loginType, String userName,
        final ILoginData data) {
        final ISimpleLogger logger = server.logger();
        if (this.version.major != version.major || this.version.minor != version.minor) {
            // This is not supported
            return ILoginResult.deny(
                "The version you are running is not supported by the server (only {1}.{2}.X).\nThe server runs on {0} and your client is on {3}.",
                this.version.toString(), this.version.major, this.version.minor, version.toString());
        }
        userName = TextFormatter.clean(userName);
        if (!(data instanceof ILoginData.Password(final String password)) || userName == null || userName.isBlank() || password == null
            || password.isEmpty()) {
            // Not initializing the client makes the login fail
            logger.warning("Login refused for '{0}' from {1}: Username or password is missing", userName, client.ip());
            return ILoginResult.ACCEPTED;
        }
        try {
            // Only used for the log output, the login itself creates the account if needed
            final boolean existed = userRepository.findAccount(userName).isPresent();
            final Optional<UserAccount> result = loginRepository.loginClaim(userName, password);
            if (result.isEmpty()) {
                // Not initializing the client makes the login fail
                logger.warning("Login refused for '{0}' from {1}: Wrong password or account is protected by an OAuth identity", userName,
                    client.ip());
                return ILoginResult.ACCEPTED;
            }
            final UserAccount account = result.get();
            if (!existed) {
                logger.info("Created new account '{0}' (id {1}) from {2}, the password is now bound to it", account.username(),
                    account.id(), client.ip());
            } else {
                final String character = userRepository.loadProfile(account.id()).map(UserProfile::nickname)
                    .map(name -> "character '" + name + "'").orElse("no character yet");
                logger.info("Login of account '{0}' (id {1}, {2}) from {3}", account.username(), account.id(), character, client.ip());
            }
            ClientActor actor = new ClientActor(snowFrame, client, account.uuid());
            permissionsConfig.load(actor.permissionSet());
            if (server.isInMaintenance() && !actor.hasPermission("server.maintenance")) {
                return ILoginResult.deny(server.maintenanceText());
            }
            client.init(version, account.username(), server.netManager().nextClientId());
        } catch (final DatabaseException exception) {
            // Not initializing the client makes the login fail
            logger.error("Database error during login of '{0}' from {1}", exception, userName, client.ip());
        }
        return ILoginResult.ACCEPTED;
    }

}
