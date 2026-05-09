package de.brickforceaurora.server.login.net;

import de.brickforceaurora.server.ILoginHandler;
import de.brickforceaurora.server.login.LoginServerApp;
import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.net.NetManager;
import me.lauriichan.snowframe.util.Version;

public class DevLoginHandler implements ILoginHandler {

    private NetManager<LoginServerApp> loginServerAppNetManager;

    public DevLoginHandler(NetManager<LoginServerApp> loginServerAppNetManager) {
        this.loginServerAppNetManager = loginServerAppNetManager;
    }

    @Override
    public void login(BFClient client, Version version, boolean session, String userName, String tokenOrPassword) {
        //LoginServerApp.logger().debug("version: " + version + " username: " + userName + " password: " + tokenOrPassword);
        client.init(version, userName, loginServerAppNetManager.nextClientId());
    }
}
