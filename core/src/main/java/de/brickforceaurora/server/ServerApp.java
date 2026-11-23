package de.brickforceaurora.server;

import java.util.concurrent.atomic.AtomicReference;

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
    
    private static AtomicReference<Version> version = new AtomicReference<>();

    public static Version version() {
        return version.get();
    }
    
    protected final MessageManager messageManager = new MessageManager();
    private ConsoleActor consoleActor;
    
    public ServerApp(Version version) {
        ServerApp.version.set(version);
    }
    
    @SuppressWarnings({
        "unchecked",
        "rawtypes"
    })
    @Override
    public void registerLifecycle(Lifecycle<A> lifecycle) {
        this.consoleActor = new ConsoleActor(lifecycle.snowFrame(), messageManager);
        lifecycle.startupChain().register("ready", Stage.PRE, frame -> {
            final SimpleMessageProviderFactory factory = new SimpleMessageProviderFactory();
            frame.extension(IMessageExtension.class, false).callClasses(extension -> {
                if (extension.isEnum()) {
                    if (!IMessageDefinition.class.isAssignableFrom(extension)) {
                        return;
                    }
                    messageManager.register(new EnumMessageSource((Class) extension, factory));
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

    public abstract ILoginHandler loginHandler();

    public abstract BrickForceServer<?> server();

    public String host() {
        return "localhost";
    }

    public int port() {
        return 18890;
    }

}
