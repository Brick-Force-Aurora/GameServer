package de.brickforceaurora.server.match.config.migration;

import de.brickforceaurora.server.match.config.HostConfig;
import me.lauriichan.snowframe.config.ConfigMigrationExtension;
import me.lauriichan.snowframe.config.Configuration;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public final class HostConfigMigrationV2 extends ConfigMigrationExtension<HostConfig> {

    public HostConfigMigrationV2() {
        super(HostConfig.class, 1, 2);
    }

    @Override
    public String description() {
        return "Move metrics.url to metrics.db.url";
    }

    @Override
    public void migrate(Configuration configuration) throws Throwable {
        configuration.set("metrics.db.url", configuration.get("metrics.url"));
        configuration.remove("metrics.url");
    }

}
