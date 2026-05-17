package de.brickforceaurora.server.net.login;

import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.snowframe.util.Version;

public interface ILoginHandler {

    final String ATTR_REQUEST_LOGIN_TIME = "login.requested_time";
    final long LOGIN_TIMEOUT_TIME = TimeUnit.MINUTES.toNanos(60);
    
    IFlags<LoginType> supportedTypes();

    void login(BFClient client, Version version, LoginType loginType, String userName, ILoginData loginData);

}
