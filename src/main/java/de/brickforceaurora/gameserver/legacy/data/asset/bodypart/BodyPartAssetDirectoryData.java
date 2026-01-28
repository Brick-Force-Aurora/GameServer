package de.brickforceaurora.gameserver.legacy.data.asset.bodypart;

import de.brickforceaurora.gameserver.legacy.data.asset.AssetDirectoryData;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public class BodyPartAssetDirectoryData extends AssetDirectoryData<BodyPartAsset> {

    public BodyPartAssetDirectoryData() {
        super("bodypart");
    }

    @Override
    protected BodyPartAsset readAsset(final ISimpleLogger logger, final FileKey key, final JsonObject root, final String name,
        final int id) {
        return new BodyPartAsset(name, id, root.getAsFloat("damage_factor", 1f));
    }

}
