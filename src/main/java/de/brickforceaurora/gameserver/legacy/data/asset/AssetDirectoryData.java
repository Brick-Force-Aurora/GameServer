package de.brickforceaurora.gameserver.legacy.data.asset;

import java.io.File;
import java.io.IOException;

import de.brickforceaurora.gameserver.GameServerApp;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import me.lauriichan.laylib.json.IJson;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.json.JsonType;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.data.DirectoryDataExtension;
import me.lauriichan.snowframe.data.IDataHandler;
import me.lauriichan.snowframe.data.handler.JsonDataHandler;

public abstract class AssetDirectoryData<A extends Asset> extends DirectoryDataExtension<IJson<?>> {

    public static final String INTERNAL_PATH = "jar://assets/%s";
    public static final String EXTERNAL_PATH = "data://assets/%s";

    private final Object2ObjectMap<String, A> assetByName = Object2ObjectMaps.synchronize(new Object2ObjectOpenHashMap<>());
    private final Int2ObjectMap<A> assetById = Int2ObjectMaps.synchronize(new Int2ObjectOpenHashMap<>());

    private final String internalPath, externalPath;
    private final JsonDataHandler dataHandler;

    private final SnowFrame<?> snowFrame;

    public AssetDirectoryData(final String key) {
        this.internalPath = INTERNAL_PATH.formatted(key);
        this.externalPath = EXTERNAL_PATH.formatted(key);
        this.dataHandler = JsonDataHandler.forKey(key);
        this.snowFrame = GameServerApp.get().snowFrame();
    }

    @Override
    public final String path() {
        return externalPath;
    }

    @Override
    public final IDataHandler<IJson<?>> handler() {
        return dataHandler;
    }

    public final A asset(final String name) {
        return assetByName.get(name);
    }

    public final A asset(final int id) {
        return assetById.get(id);
    }

    @Override
    public boolean isSupported(final File file, final String name, final String extension, final boolean isFile) {
        if (!isFile) {
            return true;
        }
        return "json".equals(extension);
    }

    @Override
    public boolean searchSupportedDirectories() {
        return true;
    }

    @Override
    public boolean saveKnownFiles() {
        return false;
    }

    @Override
    public void onLoadStart(final ISimpleLogger logger) {
        assetByName.clear();
        assetById.clear();

        try {
            snowFrame.externalResource(internalPath, externalPath, true);
        } catch (final IOException e) {
            logger.error("Failed to extract assets", e);
        }
    }

    @Override
    public void onLoad(final ISimpleLogger logger, final FileData<IJson<?>> value) throws Exception {
        final IJson<?> json = value.value();
        if (json == null || !json.isObject()) {
            logger.warning("Invalid json value for asset '{0}'", value.key().location().key());
            return;
        }
        final JsonObject root = json.asJsonObject();
        if (!root.has("name", JsonType.STRING)) {
            logger.warning("Invalid json value for asset '{0}': name is not set", value.key().location().key());
            return;
        }
        if (!root.has("id", JsonType.NUMBER)) {
            logger.warning("Invalid json value for asset '{0}': id is not set", value.key().location().key());
            return;
        }
        final String name = root.getAsString("name");
        if (assetByName.containsKey(name)) {
            logger.warning("Duplicate asset name for asset '{0}': {1}", value.key().location().key(), name);
            return;
        }
        final int id = root.getAsInt("id");
        if (assetById.containsKey(id)) {
            logger.warning("Duplicate asset id for asset '{0}': {1}", value.key().location().key(), id);
            return;
        }
        final A asset = readAsset(logger, value.key(), root, name, id);
        if (asset == null) {
            return;
        }
        assetByName.put(name, asset);
        assetById.put(id, asset);
    }

    protected abstract A readAsset(ISimpleLogger logger, FileKey key, JsonObject root, String name, int id);

}
