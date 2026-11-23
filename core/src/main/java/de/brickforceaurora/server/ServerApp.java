package de.brickforceaurora.server;

import de.brickforceaurora.server.command.ConsoleActor;
import de.brickforceaurora.server.command.message.IMessageExtension;
import de.brickforceaurora.server.command.message.SimpleMessageProviderFactory;
import de.brickforceaurora.server.net.tcp.BrickForceServer;
import de.brickforceaurora.server.net.tcp.login.ILoginHandler;
import me.lauriichan.laylib.localization.MessageManager;
import me.lauriichan.laylib.localization.source.AnnotationMessageSource;
import me.lauriichan.laylib.localization.source.EnumMessageSource;
import me.lauriichan.laylib.localization.source.IMessageDefinition;
import me.lauriichan.snowframe.ISnowFrameApp;
import me.lauriichan.snowframe.lifecycle.Lifecycle;
import me.lauriichan.snowframe.lifecycle.LifecyclePhase.Stage;
import me.lauriichan.snowframe.util.Version;

public abstract class ServerApp<A extends ServerApp<A>> implements ISnowFrameApp<A> {

    protected final MessageManager messageManager = new MessageManager();
    private ConsoleActor consoleActor;
    
    @Override
    public void registerLifecycle(Lifecycle<A> lifecycle) {
        this.consoleActor = new ConsoleActor(lifecycle.snowFrame(), messageManager);
        lifecycle.startupChain().register("ready", Stage.PRE, frame -> {
            final SimpleMessageProviderFactory factory = new SimpleMessageProviderFactory();
            frame.extension(IMessageExtension.class, true).callClasses(extension -> {
                if (extension.isEnum()) {
                    if (!IMessageDefinition.class.isAssignableFrom(extension)) {
                        return;
                    }
                    messageManager.register(new EnumMessageSource(extension.asSubclass(Enum.class), factory));
                    return;
                }
                messageManager.register(new AnnotationMessageSource(extension, factory));
            });
        });
    }

    public final ConsoleActor consoleActor() {
        return consoleActor;
    }
    
    public final MessageManager messageManager() {
        return messageManager;
    }
    
    public abstract Version version();

    public abstract ILoginHandler loginHandler();

    public abstract BrickForceServer<?> server();

    public String host() {
        return "localhost";
    }

    public int port() {
        return 18890;
    }

}
