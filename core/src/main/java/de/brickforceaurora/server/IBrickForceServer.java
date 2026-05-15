package de.brickforceaurora.server;

import de.brickforceaurora.server.net.login.ILoginHandler;

public interface IBrickForceServer {
    
    ILoginHandler loginHandler();

}
