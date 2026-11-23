package de.brickforceaurora.server.net.tcp.login;

import java.util.Optional;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.LoginRepository;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.UserAccount;
import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.NetManager;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.util.Version;

public final class ClaimPasswordLoginHandler implements ILoginHandler {

    private final NetManager<?> netManager;
    private final UserRepository users;
    private final LoginRepository logins;
    private final IFlags<LoginType> supportedTypes = LoginType.MANAGER.newImmutable(LoginType.PASSWORD.mask());

    public ClaimPasswordLoginHandler(final NetManager<?> netManager, final UserRepository users, final LoginRepository logins) {
        this.netManager = netManager;
        this.users = users;
        this.logins = logins;
    }

    @Override
    public IFlags<LoginType> supportedTypes() {
        return supportedTypes;
    }

    @Override
    public void login(final BFClient client, final Version version, final LoginType loginType, final String userName,
        final ILoginData data) {
        final ISimpleLogger logger = netManager.logger();
        if (!(data instanceof ILoginData.Password(final String password)) || userName == null || userName.isBlank() || password == null
            || password.isEmpty()) {
            // Not initializing the client makes the login fail
            logger.warning("Login refused for '{0}' from {1}: Username or password is missing", userName, client.ip());
            return;
        }
        try {
            // Only used for the log output, the login itself creates the account if needed
            final boolean existed = users.findAccount(userName).isPresent();
            final Optional<UserAccount> result = logins.loginClaim(userName, password);
            if (result.isEmpty()) {
                // Not initializing the client makes the login fail
                logger.warning("Login refused for '{0}' from {1}: Wrong password or account is protected by an OAuth identity", userName,
                    client.ip());
                return;
            }
            final UserAccount account = result.get();
            if (!existed) {
                logger.info("Created new account '{0}' (id {1}) from {2}, the password is now bound to it", account.username(),
                    account.id(), client.ip());
            } else {
                final String character = users.loadProfile(account.id()).map(UserProfile::nickname).map(name -> "character '" + name + "'")
                    .orElse("no character yet");
                logger.info("Login of account '{0}' (id {1}, {2}) from {3}", account.username(), account.id(), character, client.ip());
            }
            client.init(version, account.username(), netManager.nextClientId());
        } catch (final DatabaseException exception) {
            // Not initializing the client makes the login fail
            logger.error("Database error during login of '{0}' from {1}", exception, userName, client.ip());
        }
    }

}
