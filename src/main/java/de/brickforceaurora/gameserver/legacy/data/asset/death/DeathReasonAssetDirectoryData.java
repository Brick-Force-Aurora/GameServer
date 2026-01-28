package de.brickforceaurora.gameserver.legacy.data.asset.death;

import de.brickforceaurora.gameserver.legacy.data.asset.AssetDirectoryData;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public final class DeathReasonAssetDirectoryData extends AssetDirectoryData<DeathReasonAsset> {

    public DeathReasonAssetDirectoryData() {
        super("death_reason");
    }

    @Override
    protected DeathReasonAsset readAsset(final ISimpleLogger logger, final FileKey key, final JsonObject root, final String name,
        final int id) {
        return new DeathReasonAsset(name, id);
    }

}
