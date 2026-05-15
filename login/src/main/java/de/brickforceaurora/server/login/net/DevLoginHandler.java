package de.brickforceaurora.server.login.net;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetManager;
import de.brickforceaurora.server.net.login.ILoginData;
import de.brickforceaurora.server.net.login.ILoginHandler;
import de.brickforceaurora.server.net.login.LoginType;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.snowframe.util.Version;

public class DevLoginHandler implements ILoginHandler {

    private final NetManager<?> netManager;
    // We don't support anything (= DEBUG login)
    private final IFlags<LoginType> supportedTypes = LoginType.MANAGER.newImmutable(0);

    public DevLoginHandler(NetManager<?> netManager) {
        this.netManager = netManager;
    }

    @Override
    public IFlags<LoginType> supportedTypes() {
        return supportedTypes;
    }

    @Override
    public void login(BFClient client, Version version, LoginType loginType, String userName, ILoginData data) {
        client.init(version, userName, netManager.nextClientId());
    }
}
