package de.brickforceaurora.gameserver.item;

import de.brickforceaurora.gameserver.GameServerApp;
import de.brickforceaurora.gameserver.item.template.TItem;
import de.brickforceaurora.gameserver.item.template.TWeapon;
import de.brickforceaurora.gameserver.util.Texture2D;
import me.lauriichan.laylib.json.IJson;
import me.lauriichan.laylib.json.JsonArray;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.json.io.JsonParser;
import me.lauriichan.laylib.json.io.JsonSyntaxException;
import me.lauriichan.snowframe.SnowFrame;
import me.lauriichan.snowframe.resource.source.IDataSource;

import java.io.IOException;
import java.io.InputStream;

public class UpgradePropManager {

    /* ===================== SINGLETON ===================== */

    private static final UpgradePropManager INSTANCE = new UpgradePropManager();

    public static UpgradePropManager getInstance() {
        return INSTANCE;
    }

    /* ===================== FIELDS ===================== */

    private UpgradeCategoryPropTable[] upgradeCatTable;
    private boolean loaded = false;

    private final SnowFrame<?> frame;

    private static final int PROP_COUNT = 13;

    /* ===================== CONSTRUCTOR ===================== */

    private UpgradePropManager() {
        this.frame = GameServerApp.get().snowFrame();
    }

    /* ===================== STATE ===================== */

    public boolean isLoaded() {
        return loaded;
    }

    /* ===================== ACCESS ===================== */

    public boolean useProp(int category, int propIndex) {
        if (!loaded || upgradeCatTable == null)
            return false;

        if (propIndex < 0 || propIndex >= upgradeCatTable.length)
            return false;

        return upgradeCatTable[propIndex].props()[category];
    }

    /* ===================== LOAD ===================== */

    private IJson<?> readJson(String path) throws IllegalStateException, IOException, JsonSyntaxException {
        IDataSource source = frame.resource(path);
        if (!source.exists()) {
            throw new IllegalStateException("File doesn't exist: " + source.getPath());
        }
        try (InputStream stream = source.openReadableStream()) {
            return JsonParser.fromStream(stream);
        }
    }

    private boolean load() {
        try {
            IJson<?> root = readJson("jar://upgradeprops.json");

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parse(root.asJsonArray());
            loaded = true;
            return true;

        } catch (Exception e) {
            loaded = false;
            e.printStackTrace();
            return false;
        }
    }

    private void parse(JsonArray array) {

        upgradeCatTable = new UpgradeCategoryPropTable[array.size()];

        int row = 0;
        for (IJson<?> elem : array) {

            JsonObject o = elem.asJsonObject();

            // name
            String name = o.getAsString("name").trim();

            // props
            JsonArray propsArray = o.getAsArray("props");
            if (propsArray == null || propsArray.size() != PROP_COUNT) {
                throw new IllegalStateException(
                        "Invalid props array for " + name +
                                " (expected " + PROP_COUNT + ")"
                );
            }

            boolean[] props = new boolean[]{
                    o.getAsBoolean("heavy"),
                    o.getAsBoolean("assault"),
                    o.getAsBoolean("sniper"),
                    o.getAsBoolean("submachine"),
                    o.getAsBoolean("pistol"),
                    o.getAsBoolean("melee"),
                    o.getAsBoolean("grenade"),
                    o.getAsBoolean("flashbang"),
                    o.getAsBoolean("smoke"),
                    o.getAsBoolean("cloth"),
                    o.getAsBoolean("accessory_helmet"),
                    o.getAsBoolean("accessory_other"),
                    o.getAsBoolean("shotgun")
            };

            UpgradeCategoryPropTable table = new UpgradeCategoryPropTable(name, props);
            upgradeCatTable[row++] = table;
        }
    }


    /* ===================== STUBS ===================== */

    private Texture2D findIcon(String name) {
        return null; // server-side stub
    }
}
