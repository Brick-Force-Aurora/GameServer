package de.brickforceaurora.server.net.tcp.login;

import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.net.tcp.BFClient;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.snowframe.util.Version;

public interface ILoginHandler {

    String ATTR_REQUEST_LOGIN_TIME = "login.requested_time";
    long LOGIN_TIMEOUT_TIME = TimeUnit.MINUTES.toNanos(60);

    IFlags<LoginType> supportedTypes();

    ILoginResult login(BFClient client, Version version, LoginType loginType, String userName, ILoginData loginData);

}
