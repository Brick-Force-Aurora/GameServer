package de.brickforceaurora.server.net.tcp;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import de.brickforceaurora.server.ServerApp;
import de.brickforceaurora.server.net.udp.RendezvousManager;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollIoHandler;
import io.netty.channel.nio.NioIoHandler;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SignalModule;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.signal.ISignal;
import me.lauriichan.snowframe.signal.SignalManager;
import me.lauriichan.snowframe.util.tick.AbstractTickTimer;

public final class BrickForceServer<S extends ServerApp<S>> extends AbstractTickTimer {

    public static record TickSignal(long delta) implements ISignal {}

    public static final long TARGET_TPS = 60;

    private static final float SECOND_IN_NANOS = TimeUnit.SECONDS.toNanos(1);

    private final MultiThreadIoEventLoopGroup group = new MultiThreadIoEventLoopGroup(
        Epoll.isAvailable() ? EpollIoHandler.newFactory() : NioIoHandler.newFactory());

    private final ISimpleLogger logger;
    private final NetManager<S> netManager;
    private final RendezvousManager<S> rendezvousManager;

    private final ServerApp<S> server;
    private final SignalManager signalManager;

    private final AtomicBoolean maintenanceEnabled = new AtomicBoolean(false);
    private final AtomicReference<String> maintenanceText = new AtomicReference<>();

    public BrickForceServer(final SnowFrame<S> frame) {
        this.server = frame.app();
        this.logger = frame.logger();
        this.signalManager = frame.module(SignalModule.class).signalManager();
        this.netManager = new NetManager<>(frame, group);
        this.rendezvousManager = new RendezvousManager<>(frame, group);
        frame.invoker().addExtra(this);

        // Setup timer
        setName("BrickForce Server");
        sync().length((long) Math.floor(SECOND_IN_NANOS / TARGET_TPS), TimeUnit.NANOSECONDS);
    }

    public boolean isInMaintenance() {
        return maintenanceEnabled.get();
    }
    
    public String maintenanceText() {
        return maintenanceText.get();
    }

    public boolean disableMaintenance() {
        return this.maintenanceEnabled.compareAndSet(true, false);
    }

    public boolean enableMaintenance() {
        return enableMaintenance(null);
    }

    public boolean enableMaintenance(String maintenanceText) {
        if (this.maintenanceEnabled.compareAndSet(false, true)) {
            this.maintenanceText.set(maintenanceText == null || maintenanceText.isBlank() ? "Server is currently closed for maintenance" : maintenanceText);
            return true;
        }
        return false;
    }

    @Override
    protected Thread createThread(final Runnable runnable) {
        return Thread.ofPlatform().unstarted(runnable);
    }

    @Override
    protected void tick(final long delta) {
        netManager.tick(delta);
        signalManager.call(new TickSignal(delta));
    }

    public void open() throws InterruptedException {
        if (!isAlive()) {
            start();
        }
        netManager.open(server.port());
        rendezvousManager.open(server.port());
    }

    public void close() throws Exception {
        if (isAlive()) {
            stop();
        }
        rendezvousManager.close();
        netManager.close();

        group.close();
    }

    public RendezvousManager<S> rendezvousManager() {
        return rendezvousManager;
    }

    public NetManager<S> netManager() {
        return netManager;
    }

    public ISimpleLogger logger() {
        return logger;
    }

}
