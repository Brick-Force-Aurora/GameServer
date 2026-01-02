package de.brickforceaurora.gameserver.item.template;

import de.brickforceaurora.gameserver.core.GameServerLogic;
import de.brickforceaurora.gameserver.item.BuffManager;
import de.brickforceaurora.gameserver.util.Texture2D;
import me.lauriichan.laylib.json.IJson;
import me.lauriichan.laylib.json.JsonArray;
import me.lauriichan.laylib.json.JsonObject;
import me.lauriichan.laylib.json.io.JsonParser;

import java.io.File;
import java.util.*;

public final class TItemManager {

    private boolean isLoaded;
    private boolean isIconLoaded;
    private boolean isWeaponByLoaded;
    private boolean isMaterialLoaded;

    public int icnVersion = 1;
    public int wpnbyVersion = 1;
    public int mtlVersion = 1;

    public String[] materials;
    public Texture2D[] icons;
    public Texture2D[] weaponBy;

    private final Map<String, TItem> dic;
    public String[] prefabs;
    public String[] coatBodyCodes;
    public String[] onlyOneCountingCodes;

    private boolean iconRequesting;

    private final Map<Integer, Integer> wpnBy2Slot;
    private final Map<Integer, Integer> wpnBy2Category;

    /* ===================== SINGLETON ===================== */

    private static volatile TItemManager instance;

    public static TItemManager getInstance() {
        if (instance == null) {
            synchronized (TItemManager.class) {
                if (instance == null) {
                    instance = new TItemManager();
                    instance.loadAll();
                }
            }
        }
        return instance;
    }

    private TItemManager() {
        this.dic = new HashMap<>();
        this.wpnBy2Slot = new HashMap<>();
        this.wpnBy2Category = new HashMap<>();
        this.iconRequesting = false;
    }

    /* ===================== GETTERS ===================== */

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean isIconLoaded() {
        return isIconLoaded;
    }

    public boolean isWeaponByLoaded() {
        return isWeaponByLoaded;
    }

    public boolean isMaterialLoaded() {
        return isMaterialLoaded;
    }

    @SuppressWarnings("unchecked")
    public <T extends TItem> T get(String key) {
        return (T) dic.get(key);
    }

    /* ===================== INTERNAL ===================== */

    private void add(String code, TItem item) {
        if (dic.containsKey(code)) {
            GameServerLogic.getInstance().logger().error("ERROR, duplicated item code " + code);
            return;
        }
        dic.put(code, item);
    }

    /* ===================== LOAD ALL ===================== */

    public void loadAll() {
        isLoaded = loadCostumeFromLocalFileSystem() && loadWeaponFromLocalFileSystem() && loadAccessoryFromLocalFileSystem() &&
                        loadCharacterFromLocalFileSystem() && loadSpecialFromLocalFileSystem() && loadUpgradeFromLocalFileSystem() &&
                        loadBundleFromLocalFileSystem();
    }

    private boolean loadBundleFromLocalFileSystem() {
        try {
            IJson<?> root = JsonParser.fromFile(
                    new File("Template/bundle.json")
            );

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parseBundle(root.asJsonArray());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parseBundle(JsonArray array) {
        for (IJson<?> elem : array) {
            JsonObject o = elem.asJsonObject();

            TBundle bundle = new TBundle(
                    o.getAsString("code").toLowerCase(),
                    o.getAsString("name"),
                    findIcon(o.getAsString("iconName")),
                    TItem.string2Type(o.getAsString("type").toLowerCase()),
                    o.getAsBoolean("isAmount"),
                    o.getAsString("comment"),
                    o.getAsInt("season"),
                    o.getAsInt("starRate"));

            add(o.getAsString("code").toLowerCase(), bundle);
        }
    }

    private boolean loadWeaponFromLocalFileSystem() {
        try {
            IJson<?> root = JsonParser.fromFile(
                    new File("Template/weapon.json")
            );

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parseWeapon(root.asJsonArray());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parseWeapon(JsonArray array) {

        for (IJson<?> elem : array) {
            JsonObject o = elem.asJsonObject();

            TWeapon weapon = new TWeapon(
                    o.getAsString("code").toLowerCase(),
                    o.getAsString("name"),
                    o.getAsString("bone"),
                    o.getAsString("prefab"),
                    o.getAsString("prefab11"),
                    findIcon(o.getAsString("icon")),
                    findIcon(o.getAsString("icon11")),
                    TItem.string2Type(o.getAsString("type")),
                    TItem.string2Kind(TItem.string2Type(o.getAsString("type")), o.getAsString("kind")),
                    TWeapon.String2WeaponCategory(o.getAsString("cat")),
                    o.getAsBoolean("takeoffable"),
                    TItem.string2Slot(o.getAsString("slot")),
                    o.getAsString("comment"),
                    BuffManager.getInstance().get(o.getAsInt("buff")),
                    o.getAsBoolean("discomposable"),
                    o.getAsString("bpBackCode"),
                    o.getAsInt("durabilityMax"),
                    TItem.string2UpgradeCategory(o.getAsString("upgradeCategory")),
                    o.getAsBoolean("isBasic"),
                    o.getAsInt("season"),
                    o.getAsString("grp1"),
                    o.getAsString("grp2"),
                    o.getAsString("grp3"),
                    o.getAsBoolean("twoHands"),
                    o.getAsInt("starRate")
            );

            add(o.getAsString("code").toLowerCase(), weapon);
        }
    }

    private boolean loadCostumeFromLocalFileSystem()
    {
        try {
            IJson<?> root = JsonParser.fromFile(
                    new File("Template/costume.json")
            );

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parseCostume(root.asJsonArray());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parseCostume(JsonArray array) {

        for (IJson<?> elem : array) {
            JsonObject o = elem.asJsonObject();

            TCostume costume = new TCostume(
                    o.getAsString("code").toLowerCase(),
                    o.getAsString("name"),
                    o.getAsString("main"),
                    o.getAsString("aux"),
                    o.getAsString("mainMat"),
                    o.getAsString("auxMat"),
                    findIcon(o.getAsString("icon")),
                    TItem.string2Type(o.getAsString("type")),
                    TItem.string2Kind(TItem.string2Type(o.getAsString("type")), o.getAsString("kind")),
                    o.getAsBoolean("takeoffable"),
                    TItem.string2Slot(o.getAsString("slot")),
                    o.getAsString("comment"),
                    BuffManager.getInstance().get(o.getAsInt("buff")),
                    o.getAsBoolean("discomposable"),
                    o.getAsString("bpBackCode"),
                    o.getAsInt("armor"),
                    TItem.string2UpgradeCategory(o.getAsString("upgradeCat")),
                    o.getAsBoolean("isBasic"),
                    o.getAsString("mark") ,
                    o.getAsString("markMat"),
                    o.getAsInt("season"),
                    o.getAsString("grp1"),
                    o.getAsString("grp2"),
                    o.getAsString("grp3"),
                    TItem.string2FunctionMask(o.getAsString("function")),
                    o.getAsInt("funcFactor"),
                    findIcon(o.getAsString("functionIcon")),
                    o.getAsInt("starRate")
            );

            add(o.getAsString("code").toLowerCase(), costume);
        }
    }


    private boolean loadAccessoryFromLocalFileSystem()
    {
        try {
            IJson<?> root = JsonParser.fromFile(
                    new File("Template/accessory.json")
            );

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parseAccessory(root.asJsonArray());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parseAccessory(JsonArray array) {

        for (IJson<?> elem : array) {
            JsonObject o = elem.asJsonObject();

            TAccessory accessory = new TAccessory(
                    o.getAsString("code").toLowerCase(),
                    o.getAsString("name"),
                    o.getAsString("bone"),
                    o.getAsString("prefab"),
                    findIcon(o.getAsString("icon")),
                    TItem.string2Type(o.getAsString("type")),
                    TItem.string2Kind(TItem.string2Type(o.getAsString("type")), o.getAsString("kind")),
                    o.getAsBoolean("takeoffable"),
                    TItem.string2Slot(o.getAsString("slot")),
                    o.getAsString("comment"),
                    BuffManager.getInstance().get(o.getAsInt("buff")),
                    o.getAsBoolean("discomposable"),
                    o.getAsString("bpBackCode"),
                    TItem.string2FunctionMask(o.getAsString("function")),
                    o.getAsInt("armor"),
                    o.getAsInt("funcFactor"),
                    TItem.string2UpgradeCategory(o.getAsString("upgradeCategory")),
                    o.getAsInt("season"),
                    o.getAsString("grp1"),
                    o.getAsString("grp2"),
                    o.getAsString("grp3"),
                    findIcon(o.getAsString("functionIcon")),
                    o.getAsInt("starRate")
            );

            add(o.getAsString("code").toLowerCase(), accessory);
        }
    }

    private boolean loadCharacterFromLocalFileSystem()
    {
        try {
            IJson<?> root = JsonParser.fromFile(
                    new File("Template/character.json")
            );

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parseCharacter(root.asJsonArray());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parseCharacter(JsonArray array) {

        for (IJson<?> elem : array) {
            JsonObject o = elem.asJsonObject();

            TCharacter character = new TCharacter(
                    o.getAsString("code").toLowerCase(),
                    o.getAsString("name"),
                    findIcon(o.getAsString("icon")),
                    TItem.string2Type(o.getAsString("type")),
                    o.getAsBoolean("takeoffable"),
                    o.getAsString("gender"),
                    o.getAsString("prefix"),
                    o.getAsString("comment"),
                    BuffManager.getInstance().get(o.getAsInt("buff")),
                    o.getAsBoolean("discomposable"),
                    o.getAsString("bpBackCode"),
                    o.getAsInt("season"),
                    o.getAsString("mainMat"),
                    o.getAsString("grp1"),
                    o.getAsString("grp2"),
                    o.getAsString("grp3"),
                    o.getAsInt("starRate")
            );

            add(o.getAsString("code").toLowerCase(), character);
        }
    }

    private boolean loadSpecialFromLocalFileSystem()
    {
        try {
            IJson<?> root = JsonParser.fromFile(
                    new File("Template/special.json")
            );

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parseSpecial(root.asJsonArray());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parseSpecial(JsonArray array) {

        for (IJson<?> elem : array) {
            JsonObject o = elem.asJsonObject();

            TSpecial special = new TSpecial(
                    o.getAsString("code").toLowerCase(),
                    o.getAsString("name"),
                    findIcon(o.getAsString("icon")),
                    TItem.string2Type(o.getAsString("type")),
                    o.getAsBoolean("isAmount"),
                    TItem.string2FunctionMask(o.getAsString("function")),
                    o.getAsString("comment"),
                    o.getAsBoolean("discomposable"),
                    o.getAsString("bpBackCode"),
                    o.getAsBoolean("isBasic"),
                    o.getAsInt("season"),
                    o.getAsString("param"),
                    o.getAsInt("starRate")
            );

            add(o.getAsString("code").toLowerCase(), special);
        }
    }

    private boolean loadUpgradeFromLocalFileSystem()
    {
        try {
            IJson<?> root = JsonParser.fromFile(
                    new File("Template/upgrade.json")
            );

            if (!root.isArray()) {
                throw new IllegalStateException("Root must be a JSON array");
            }

            parseUpgrade(root.asJsonArray());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void parseUpgrade(JsonArray array) {

        for (IJson<?> elem : array) {
            JsonObject o = elem.asJsonObject();

            TUpgrade upgrade = new TUpgrade(
                    o.getAsString("code").toLowerCase(),
                    o.getAsString("name"),
                    findIcon(o.getAsString("icon")),
                    TItem.string2Type(o.getAsString("type")),
                    o.getAsInt("tier"),
                    o.getAsString("target"),
                    o.getAsInt("playerLv"),
                    o.getAsInt("reqLv"),
                    o.getAsInt("maxLv"),
                    o.getAsString("comment"),
                    o.getAsInt("starRate")
            );

            add(o.getAsString("code").toLowerCase(), upgrade);
        }
    }

    /* ===================== STUBS ===================== */

    private Texture2D findIcon(String name) {
        return null; // server-side stub
    }
}
