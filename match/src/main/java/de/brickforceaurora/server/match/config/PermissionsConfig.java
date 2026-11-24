package de.brickforceaurora.server.match.config;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.config.Configuration;
import me.lauriichan.snowframe.config.IConfigHandler;
import me.lauriichan.snowframe.config.ISingleConfigExtension;
import me.lauriichan.snowframe.config.handler.JsonConfigHandler;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public final class PermissionsConfig implements ISingleConfigExtension {

    private final Object2ObjectMap<UUID, List<String>> permissions = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());
    private volatile boolean modified = false;
    
    public void update(PermissionSet set) {
        if (!set.isDirty()) {
            return;
        }
        try {
            List<String> list = set.asList();
            if (list == null) {
                permissions.remove(set.owner());
                return;
            }
            permissions.put(set.owner(), Collections.unmodifiableList(list));
        } finally {
            modified = true;
        }
    }
    
    public void load(PermissionSet set) {
        set.overwrite(permissions.getOrDefault(set.owner(), Collections.emptyList()));
    }
    
    @Override
    public boolean isModified() {
        return modified;
    }

    @Override
    public IConfigHandler handler() {
        return JsonConfigHandler.JSON;
    }

    @Override
    public String path() {
        return "config://permissions.json";
    }

    @Override
    public void onPropergate(ISimpleLogger logger, Configuration configuration) throws Exception {
        configuration.set("permissions.example", List.of("example.permission", "example.permission2"));
    }

    @Override
    public void onLoad(ISimpleLogger logger, Configuration configuration) throws Exception {
        Configuration permissionSection = configuration.getConfiguration("permissions", true);
        for (String key : permissionSection.keySet()) {
            if (key.equals("example")) {
                continue;
            }
            UUID uuid;
            try {
                uuid = UUID.fromString(key);
            } catch (IllegalArgumentException iae) {
                logger.warning("Invalid permission entry found", iae);
                continue;
            }
            permissions.put(uuid, Collections.unmodifiableList(configuration.getList(key, String.class)));
        }
    }

    @Override
    public void onSave(ISimpleLogger logger, Configuration configuration) throws Exception {
        configuration.remove("permissions");
        onPropergate(logger, configuration);
        Configuration permissionSection = configuration.getConfiguration("permissions", true);
        for (Object2ObjectMap.Entry<UUID, List<String>> entry : permissions.object2ObjectEntrySet()) {
            permissionSection.set(entry.getKey().toString(), entry.getValue());
        }
    }

}
