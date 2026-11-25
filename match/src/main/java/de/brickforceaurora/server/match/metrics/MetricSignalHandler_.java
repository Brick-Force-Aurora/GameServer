package de.brickforceaurora.server.match.metrics;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import de.brickforceaurora.server.match.MatchServerApp;
import de.brickforceaurora.server.match.config.HostConfig;
import de.brickforceaurora.server.net.tcp.BrickForceServer.TickSignal;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.ConfigModule;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.extension.Extension;
import me.lauriichan.snowframe.signal.ISignalHandler;
import me.lauriichan.snowframe.signal.SignalContext;
import me.lauriichan.snowframe.signal.SignalHandler;
import me.lauriichan.snowframe.util.http.HttpMethod;
import me.lauriichan.snowframe.util.http.HttpRequest;
import me.lauriichan.snowframe.util.http.HttpResponse;
import me.lauriichan.snowframe.util.http.type.HttpContentType;
import me.lauriichan.snowframe.util.tick.TickCounter;

@Extension
public class MetricSignalHandler_ implements ISignalHandler {

    private final TickCounter counter = new TickCounter(10, TimeUnit.SECONDS);

    private final HttpRequest request = new HttpRequest().method(HttpMethod.POST);

    private final ISimpleLogger logger;
    private final MatchServerApp app;
    private final HostConfig config;

    public MetricSignalHandler_(SnowFrame<MatchServerApp> snowFrame) {
        this.app = snowFrame.app();
        this.logger = snowFrame.logger();
        this.config = snowFrame.module(ConfigModule.class).manager().config(HostConfig.class);
    }

    @SignalHandler
    public void onTick(SignalContext<TickSignal> signal) {
        if (!counter.tick(signal.signal().delta())) {
            return;
        }
        doMetricsDB();
    }
    
    private void appendMetricsDB(StringBuilder builder) {
        append(builder, "players_online", app.server().netManager().clientCount());
        append(builder, "rooms", app.roomManager().roomCount());
    }
    
    private void doMetricsDB() {
        String metricsUrl = config.metricsUrl();
        if (metricsUrl == null || metricsUrl.isBlank()) {
            return;
        }
        request.url(metricsUrl);
        StringBuilder metricsBuilder = new StringBuilder();
        appendMetricsDB(metricsBuilder);
        try {
            HttpResponse<String> response = request.call(HttpContentType.TEXT, HttpContentType.TEXT,
                metricsBuilder.insert(0, "brickforce_stats,server=playtest ").toString());
            if (response.code().code() == 204) {
                return;
            }
            logger.debug("Something went wrong when pushing metrics (%s %s): %s", response.code().code(), response.code().name(),
                response.data().value());
        } catch (IOException e) {
            logger.debug("Failed to push metrics", e);
        }
    }

    private void append(StringBuilder builder, String name, Object data) {
        if (!builder.isEmpty()) {
            builder.append(',');
        }
        builder.append(name).append('=').append(data);
    }

}
