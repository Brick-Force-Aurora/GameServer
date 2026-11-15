package de.brickforceaurora.server;

import de.brickforceaurora.server.net.BrickForceServer;
import de.brickforceaurora.server.net.login.ILoginHandler;

public interface IBrickForceServer {
    
    ILoginHandler loginHandler();
    
    BrickForceServer<?> server();
    
    default String host() {
        return "localhost";
    }
    
    default int port() {
        return 18890;
    }

}
