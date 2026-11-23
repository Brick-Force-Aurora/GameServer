package de.brickforceaurora.server.net.tcp.login;

import de.brickforceaurora.server.net.tcp.BFClient;
import me.lauriichan.snowframe.util.Version;

public interface IOAuthLoginHandler extends ILoginHandler {

    /**
     * Generates an oauth request
     * 
     * @param  client  the client who requests it
     * @param  version the version of the client
     * 
     * @return         the full verification uri
     */
    String generateOAuthRequest(BFClient client, Version version);

}
