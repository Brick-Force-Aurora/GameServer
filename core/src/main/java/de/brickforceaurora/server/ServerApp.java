package de.brickforceaurora.server;

import de.brickforceaurora.server.net.tcp.BrickForceServer;
import de.brickforceaurora.server.net.tcp.login.ILoginHandler;
import me.lauriichan.laylib.localization.MessageManager;
import me.lauriichan.snowframe.ISnowFrameApp;

public abstract class ServerApp<A extends ServerApp<A>> implements ISnowFrameApp<A> {

    protected final MessageManager messageManager = new MessageManager();

    public final MessageManager messageManager() {
        return messageManager;
    }

    public abstract ILoginHandler loginHandler();

    public abstract BrickForceServer<?> server();

    public String host() {
        return "localhost";
    }

    public int port() {
        return 18890;
    }

}
