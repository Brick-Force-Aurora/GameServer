package de.brickforceaurora.server.login.net;

import de.brickforceaurora.server.ILoginHandler;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetManager;
import de.brickforceaurora.server.net.protocol.data.LoginType;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.snowframe.util.Version;

public class DevLoginHandler implements ILoginHandler {

    private final NetManager<?> netManager;
    private final IFlags<LoginType> supportedTypes = LoginType.MANAGER.newMutable().set(LoginType.PASSWORD, true).immutable();

    public DevLoginHandler(NetManager<?> netManager) {
        this.netManager = netManager;
    }

    @Override
    public IFlags<LoginType> supportedTypes() {
        return supportedTypes;
    }

    @Override
    public void login(BFClient client, Version version, LoginType loginType, String userName, String tokenOrPassword) {
        client.init(version, userName, netManager.nextClientId());
    }
}
