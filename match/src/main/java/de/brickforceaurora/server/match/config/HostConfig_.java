package de.brickforceaurora.server.match.config;

import me.lauriichan.snowframe.config.Config;
import me.lauriichan.snowframe.config.ConfigValue;
import me.lauriichan.snowframe.config.IConfigHandler;
import me.lauriichan.snowframe.config.ISingleConfigExtension;
import me.lauriichan.snowframe.config.handler.JsonConfigHandler;

@Config(automatic = true)
public class HostConfig_ implements ISingleConfigExtension {

    @ConfigValue("host")
    public String host = "127.0.0.1";

    @ConfigValue("port")
    public int port = 18890;

    @Override
    public IConfigHandler handler() {
        return JsonConfigHandler.JSON;
    }

    @Override
    public String path() {
        return "config://host.json";
    }

}
