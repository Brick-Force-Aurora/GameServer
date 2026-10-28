package de.brickforceaurora.server.net.login;

import de.brickforceaurora.server.database.DatabaseException;
import de.brickforceaurora.server.database.LoginRepository;
import de.brickforceaurora.server.database.UserRepository;
import de.brickforceaurora.server.database.model.UserAccount;
import de.brickforceaurora.server.database.model.UserProfile;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetManager;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.util.Version;

import java.util.Optional;

public final class DevLoginHandler implements ILoginHandler {

    private final NetManager<?> netManager;
    private final UserRepository users;
    private final LoginRepository logins;
    // We don't support anything (= DEBUG login)
    private final IFlags<LoginType> supportedTypes = LoginType.MANAGER.newImmutable(0);

    public DevLoginHandler(NetManager<?> netManager) {
        this(netManager, null, null);
    }

    public DevLoginHandler(NetManager<?> netManager, UserRepository users, LoginRepository logins) {
        this.netManager = netManager;
        this.users = users;
        this.logins = logins;
    }

    @Override
    public IFlags<LoginType> supportedTypes() {
        return supportedTypes;
    }

    @Override
    public void login(BFClient client, Version version, LoginType loginType, String userName, ILoginData data) {
        if (logins == null) {
            client.init(version, userName, netManager.nextClientId());
            return;
        }
        ISimpleLogger logger = netManager.logger();
        try {
            // Only used for the log output, the login itself creates the account if needed
            boolean existed = users.findAccount(userName).isPresent();
            Optional<UserAccount> result = logins.loginDebug(userName);
            if (result.isEmpty()) {
                // Not initializing the client makes the login fail
                logger.warning("Debug login refused for '{0}' from {1}: Account is protected by a password or OAuth identity", userName,
                        client.ip());
                return;
            }
            UserAccount account = result.get();
            if (!existed) {
                logger.info("Debug login created new account '{0}' (id {1}) from {2}", account.username(), account.id(), client.ip());
            } else {
                String character = users.loadProfile(account.id()).map(UserProfile::nickname).map(name -> "character '" + name + "'")
                        .orElse("no character yet");
                logger.info("Debug login of existing account '{0}' (id {1}, {2}) from {3}", account.username(), account.id(), character,
                        client.ip());
            }
            client.init(version, account.username(), netManager.nextClientId());
        } catch (DatabaseException exception) {
            // Not initializing the client makes the login fail
            logger.error("Database error during debug login of '{0}' from {1}", exception, userName, client.ip());
        }
    }
}
