package de.brickforceaurora.gameserver.legacy.data.asset.weapon;

import de.brickforceaurora.gameserver.legacy.data.asset.AssetDirectoryData;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public final class WeaponAssetDirectoryData extends AssetDirectoryData<WeaponAsset> {

    public WeaponAssetDirectoryData() {
        super("weapon");
    }

    @Override
    protected WeaponAsset readAsset(final ISimpleLogger logger, final FileKey key, final JsonObject root, final String name, final int id) {
        return new WeaponAsset(name, id);
    }

}
