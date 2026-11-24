package de.brickforceaurora.server.net.tcp.login;

import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.net.tcp.NetManager;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.snowframe.util.Version;

public final class DevLoginHandler implements ILoginHandler {

    private final NetManager<?> netManager;
    // We don't support anything (= DEBUG login)
    private final IFlags<LoginType> supportedTypes = LoginType.MANAGER.newImmutable(0);

    public DevLoginHandler(final NetManager<?> netManager) {
        this.netManager = netManager;
    }

    @Override
    public IFlags<LoginType> supportedTypes() {
        return supportedTypes;
    }

    @Override
    public ILoginResult login(BFClient client, Version version, LoginType loginType, String userName, ILoginData data) {
        client.init(version, userName, netManager.nextClientId());
        return ILoginResult.ACCEPTED;
    }
}
