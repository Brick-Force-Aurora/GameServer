package de.brickforceaurora.server;

import de.brickforceaurora.server.net.BFClient;
import me.lauriichan.snowframe.util.Version;

public interface ILoginHandler {

    void login(BFClient client, Version version, boolean session, String userName, String tokenOrPassword);

}
