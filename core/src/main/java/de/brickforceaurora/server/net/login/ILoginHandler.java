package de.brickforceaurora.server.net.login;

import de.brickforceaurora.server.net.BFClient;
import de.brickforceaurora.server.util.flag.IFlags;
import me.lauriichan.snowframe.util.Version;

public interface ILoginHandler {
    
    IFlags<LoginType> supportedTypes();

    void login(BFClient client, Version version, LoginType loginType, String userName, ILoginData loginData);

}
