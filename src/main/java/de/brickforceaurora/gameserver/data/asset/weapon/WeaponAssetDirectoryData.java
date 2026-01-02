package de.brickforceaurora.gameserver.data.asset.weapon;

import de.brickforceaurora.gameserver.data.asset.AssetDirectoryData;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.logger.ISimpleLogger;
import me.lauriichan.snowframe.extension.Extension;

@Extension
public final class WeaponAssetDirectoryData extends AssetDirectoryData<WeaponAsset> {

    public WeaponAssetDirectoryData() {
        super("weapon");
    }

    @Override
    protected WeaponAsset readAsset(ISimpleLogger logger, FileKey key, JsonObject root, String name, int id) {
        return new WeaponAsset(name, id);
    }

}
