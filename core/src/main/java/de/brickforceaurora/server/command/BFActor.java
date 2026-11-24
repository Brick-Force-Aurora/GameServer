package de.brickforceaurora.server.command;

import java.util.UUID;

import de.brickforceaurora.server.ServerApp;
import me.lauriichan.laylib.command.Actor;
import me.lauriichan.snowframe.SnowFrame;

public abstract class BFActor<A extends ServerApp<A>, T> extends Actor<T> {

    @SuppressWarnings("rawtypes")
    private static BFActor empty;

    @SuppressWarnings({
        "unchecked",
        "rawtypes"
    })
    private static <A extends ServerApp<A>, C> BFActor<A, C> empty(SnowFrame<A> snowFrame) {
        if (empty == null) {
            empty = new BFActor(snowFrame, null) {
                @Override
                public UUID getId() {
                    return null;
                }

                @Override
                public String getLanguage() {
                    return null;
                }

                @Override
                public String getName() {
                    return null;
                }
            };
        }
        return (BFActor<A, C>) empty;
    }

    protected final SnowFrame<A> snowFrame;

    public BFActor(SnowFrame<A> snowFrame, T handle) {
        super(handle, snowFrame.app().messageManager());
        this.snowFrame = snowFrame;
    }

    @SuppressWarnings("unchecked")
    public final <C> BFActor<A, C> asBf(Class<C> type) {
        if (handle != null && type.isAssignableFrom(handle.getClass())) {
            return (BFActor<A, C>) this;
        }
        return empty(snowFrame);
    }

    public final SnowFrame<A> snowFrame() {
        return snowFrame;
    }

    public final A app() {
        return snowFrame.app();
    }

}
