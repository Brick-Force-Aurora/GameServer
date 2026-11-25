package de.brickforceaurora.server.match.config;

import me.lauriichan.snowframe.config.Config;
import me.lauriichan.snowframe.config.ConfigValue;
import me.lauriichan.snowframe.config.IConfigHandler;
import me.lauriichan.snowframe.config.ISingleConfigExtension;
import me.lauriichan.snowframe.config.handler.JsonConfigHandler;

@Config(automatic = true)
public class HostConfig_ implements ISingleConfigExtension {

    @ConfigValue("server.host")
    public String host = "localhost";
    @ConfigValue("server.port")
    public int port = 18890;

    @ConfigValue("metrics.url")
    public String metricsUrl;

    @ConfigValue("server.maintenance.enabled")
    public boolean maintenanceEnabled = false;
    @ConfigValue("server.maintenance.text")
    public String maintenanceText = "";

    @Override
    public IConfigHandler handler() {
        return JsonConfigHandler.JSON;
    }

    @Override
    public String path() {
        return "config://host.json";
    }

}
